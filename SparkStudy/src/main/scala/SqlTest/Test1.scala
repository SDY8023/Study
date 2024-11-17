package SqlTest

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author 18438
 * @date 2024/11/16 9:33
 * @description
 */
case class T1(a:Int,b:String,c:Int)
object Test1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("TestSql")
    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()
    spark.sparkContext.setLogLevel("error")
    practice5(spark)
  }

  /**
   * 行列转换
   */
  def practice1(spark:SparkSession): Unit = {
    val data: Seq[T1] = Seq(
      T1(2014, "B", 9),
      T1(2015, "A", 8),
      T1(2014, "A", 10),
      T1(2015, "B", 7),
      T1(2014,"B",6)
    )
    import spark.implicits._
    val df = data.toDF()
    df.createOrReplaceTempView("test1")

    /**
     * 问题一：多行转多列
     */
    println("问题一：多行转多列")
    val frame1 = spark.sql(
      s"""
         |select a,
         |max(case when b = 'A' then c end) as col_A,
         |max(case when b = 'B' then c end) as col_B
         |from test1
         |group by a
         |""".stripMargin)
    frame1.show()
    frame1.createOrReplaceTempView("test2")
    println("问题二：如何将结果转成源表？（多列转多行）")
    spark.sql(
      s"""
         |select a,'A' as b,col_A as c
         |from test2
         |union all
         |select a,'B' as b,col_B as c
         |from test2
         |""".stripMargin).show()

    println("问题三：同一部门会有多个绩效，求多行转多列结果")
    spark.sql(
      s"""
         |select a,max(case when b = 'A' then c end) as col_A,
         |max(case when b = 'B' then c end) as col_B
         |from
         |(
         |  select a,b,concat_ws(',',collect_list(c)) as c
         |  from test1
         |  group by a,b
         |)t1
         |group by a
         |""".stripMargin).show()
  }

  /**
   * 排名中取他值
   * @param spark
   */
  def practice2(spark:SparkSession): Unit = {
     val data = Seq(
       T1(2014, "A", 3),
       T1(2014, "B", 1),
       T1(2014, "C", 2),
       T1(2015, "A", 4),
       T1(2015, "D", 3)
     )

    import spark.implicits._
    val frame = data.toDF()
    frame.createOrReplaceTempView("test1")

    println("问题一：按a分组取b字段最小时对应的c字段")
    val df1 = spark.sql(
      s"""
         |select a,b,c,row_number() over(partition by a order by b) as rn,
         |row_number() over(partition by a order by b desc) as rn1
         |from test1
         |""".stripMargin)
    df1.show()
    df1.createOrReplaceTempView("test2")
    spark.sql(
      s"""
         |select *
         |from test2
         |where rn = 2
         |""".stripMargin).show()

    println(s"按a分组取b字段最小和最大时对应的c字段")
    spark.sql(
      s"""
         |select *
         |from test2
         |where rn = 1
         |union
         |select *
         |from test2
         |where rn1 = 1
         |""".stripMargin).show()

    println("按a分组取b字段第二小和第二大时对应的c字段")
    spark.sql(
      s"""
         |
         |select *
         |from test2
         |where rn = 2 or rn1 = 2
         |""".stripMargin).show()

  }

  /**
   * 累计求值
   * @param spark
   */
  def practice3(spark:SparkSession): Unit = {
    val data = Seq(
      T1(2014, "A", 3),
      T1(2014, "B", 1),
      T1(2014, "C", 2),
      T1(2015, "A", 4),
      T1(2015, "D", 3)
    )
    import spark.implicits._
    val frame = data.toDF()
    frame.createOrReplaceTempView("test1")

    println("按a分组按b字段排序，对c累计求和")
    spark.sql(
      s"""
         |select a,b,c,sum(c) over(partition by a order by b) as sum_d
         |from test1
         |""".stripMargin).show()

    println("按a分组按b字段排序，对c取累计平均值")
    spark.sql(
      s"""
         |  select a,b,c,
         |  avg(c) over(partition by a order by b) as avg_c
         |  from test1
         |""".stripMargin).show()
    spark.sql(
      s"""
         |select a,b,c,sum_c / count_c as avg_c
         |from
         |(
         |  select a,b,c,
         |  sum(c) over(partition by a order by b) as sum_c,
         |  count(1) over(partition by a order by b) as count_c
         |  from test1
         |)t1
         |""".stripMargin).show()

    println("按a分组按b字段排序，对b取累计排名比例")
    spark.sql(
      s"""
         |select a,b,c,num_c / count_c as avg_c
         |from
         |(
         |  select a,b,c,
         |  row_number() over(partition by a order by b) as num_c,
         |  count(1) over(partition by a) as count_c
         |  from test1
         |)t1
         |""".stripMargin).show()

    println("按a分组按b字段排序，对b取累计求和比例")

    spark.sql(
      s"""
         |select a,b,c,
         |count(1) over(partition by a order by b) as count_a_c,
         |count(1) over(partition by a) as count_c,
         |(count(1) over(partition by a order by b)) / (count(1) over(partition by a)) as ratio_c
         |from test1
         |
         |""".stripMargin).show()





  }

  /**
   * 窗口大小控制
   * @param spark
   */
  def practice4(spark:SparkSession): Unit = {
    val data = Seq(
      T1(2014, "A", 3),
      T1(2014, "B", 1),
      T1(2014, "C", 2),
      T1(2015, "A", 4),
      T1(2015, "D", 3)
    )
    import spark.implicits._
    val frame = data.toDF()
    frame.createOrReplaceTempView("test1")

    println("问题一：按a分组按b字段排序，对c取前后各一行的和")
    spark.sql(
      s"""
         |
         |select a,b,c,
         |lag(c,1,0) over(partition by a order by b) as lag_d,
         |lead(c,1,0) over(partition by a order by b) as lead_d,
         |(lag(c,1,0) over(partition by a order by b)) + (lead(c,1,0) over(partition by a order by b)) as d
         |from test1
         |""".stripMargin).show()

    println("问题二：按a分组按b字段排序，对c取平均值,前一行与当前行的均值！")

    spark.sql(
      s"""
         |select a,b,c,
         |lag(c,1,0) over(partition by a order by b) as lag_d
         |from test1
         |""".stripMargin).show()
    spark.sql(
      s"""
         |select a,b,c,lag_d,
         |case when lag_d = 0 then c else ((lag(c,1,0) over(partition by a order by b)) + c) / 2 end as avg_c_c
         |from
         |(
         |  select a,b,c,
         |  lag(c,1,0) over(partition by a order by b) as lag_d
         |  from test1
         |)t1
         |""".stripMargin).show()

  }

  /**
   * 产生连续数值
   * @param spark
   */
  def practice5(spark:SparkSession): Unit = {

    println("不借助其他任何外表，实现产生连续数值")
    spark.sql(
      s"""
         |select id_start + pos as id,
         |val as val
         |from
         |(
         |  select
         |  0 as id_start,
         |  10000 as id_end
         |)t1
         |lateral view posexplode(split(space(id_end - id_start),'')) as pos,val
         |""".stripMargin).show(10000)

    spark.sql(
      s"""
         |select row_number() over(order by n) as rn
         |from
         |(
         |  select split(space(100),'') as x
         |)t
         |lateral view explode(x) as n
         |""".stripMargin).show(1000)
  }

}
