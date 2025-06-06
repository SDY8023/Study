package SparkTest

import java.util.Date

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author SDY
 * @title
 * @date 2024/11/5 20:13
 */
case class Company(id:Int,company:String,salary:Int)
case class T1(userId:Int,date:Int,amt:Int)
case class T2(userId:Int,date:Int,amt:Option[Int])
case class T3(id:Int,dt:String,lowcarbon:Int)
case class T4(id:Int,ts:Long)
case class T5(id:Int,dt:String)
case class T6(id:String,stt:String,edt:String)
case class T7(id:Int,stt:String,edt:String)
case class T8(a:String,b:String,c:String)
object TestSql {


  def analysis1(spark:SparkSession):Unit = {
    import spark.implicits._
    val data = Seq(
      Company(1,"A",9000),
      Company(2,"A",3000),
      Company(3,"A",6000),
      Company(4,"A",8000),
      Company(5,"A",5000),
      Company(6,"A",2000)
    )

    val dataDF = data.toDF()
    dataDF.createOrReplaceTempView("test1")
    dataDF.orderBy("salary").show()

    spark.sql(
      s"""
         |select company,percentile(salary,0.5) as d
         |from test1
         |group by company
         |""".stripMargin).show(false)
  }

  def analysis2(spark:SparkSession):Unit = {
    import spark.implicits._
    val data = Seq(
      T1(1,20170101,100),
      T1(3,20170101,20),
      T1(4,20170101,30),
      T1(1,20170102,200),
      T1(2,20170102,240),
      T1(3,20170102,30),
      T1(4,20170102,2),
      T1(1,20170103,180),
      T1(2,20170103,250),
      T1(3,20170103,30),
      T1(4,20170103,260)
    )
    val dataDF = data.toDF()
    dataDF.createOrReplaceTempView("test1")
    dataDF.show()


    spark.sql(
      s"""
         |select userId,r,count(d) as c
         |from
         |(
         |  select *,rn - rn1 as r
         |  from
         |  (
         |    select *,row_number() over(partition by userId order by date) as rn,
         |    row_number() over(partition by userId,flag order by date) as rn1
         |    from
         |    (
         |      select *,amt-d as differ_v,(amt-d)/amt as grouth_yield,
         |      case when (amt-d)/amt >= 0.5 then 0 else 1 end as flag
         |      from
         |      (
         |        select *,lag(amt,1,0) over(partition by userId order by date) as d
         |        from test1
         |      )t1
         |    )tt1
         |  )ttt1
         |)tttt1
         |group by userId,r
         |""".stripMargin).show()

  }

  def analysis3(spark:SparkSession): Unit ={
    val data = Seq(
      T2(1, 20170101, None),
      T2(3, 20170101, Some(20)),
      T2(4, 20170101, Some(30)),
      T2(1, 20170102, Some(200)),
      T2(2, 20170102, Some(240)),
      T2(3, 20170102, None),
      T2(4, 20170102, Some(2)),
      T2(1, 20170103, Some(180)),
      T2(2, 20170103, Some(250)),
      T2(3, 20170103, Some(30)),
      T2(4, 20170103, Some(260))
    )
    import spark.implicits._
    val dataFrame1 = data.toDF()
    dataFrame1.createOrReplaceTempView("test1")
    spark.sql(
      s"""
         |select count(1) a,count(*) b,count(amt) c
         |from test1
         |""".stripMargin).show()

  }

  def analysis4(spark:SparkSession): Unit ={
    val data = Seq(
      T3(1001, "2021-12-12", 123),
      T3(1002, "2021-12-12", 45),
      T3(1001, "2021-12-13", 43),
      T3(1001, "2021-12-13", 45),
      T3(1001, "2021-12-13", 23),
      T3(1002, "2021-12-14", 45),
      T3(1001, "2021-12-14", 230),
      T3(1002, "2021-12-15", 45),
      T3(1001, "2021-12-15", 23)
    )

    import spark.implicits._
    val df1 = data.toDF()
    df1.createOrReplaceTempView("test1")

    spark.sql(
      s"""
         |select id,differ,count(1) as c
         |from
         |(
         |  select *,rn1-rn2 as differ
         |  from
         |  (
         |    select *,row_number() over(partition by id order by dt) as rn1,
         |    row_number() over(partition by id,flag order by dt) as rn2
         |    from
         |    (
         |      select id,dt,sum(lowcarbon) as sum_lowcarbon,
         |      case when sum(lowcarbon) > 100 then 0 else 1 end as flag
         |      from test1
         |      group by id,dt
         |    )t1
         |    where flag = 0
         |  )tt1
         |)ttt1
         |group by id,differ
         |having count(1) >= 3
         |""".stripMargin).show()

  }

  def analysis5(spark:SparkSession): Unit ={
    val data = Seq(
      T4(1001,17523641234L),
      T4(1001,17523641256L),
      T4(1002,17523641278L),
      T4(1001,17523641334L),
      T4(1002,17523641434L),
      T4(1001,17523641534L),
      T4(1001,17523641544L),
      T4(1002,17523641634L),
      T4(1001,17523641638L),
      T4(1001,17523641654L)
    )

    import spark.implicits._

    val df1 = data.toDF()

    df1.createOrReplaceTempView("test1")

    spark.sql(
      s"""
         |select *,sum(if(diff_v >= 60,1,0)) over(partition by id order by ts) as group_flag
         |from
         |(
         |  select *,ts -ts2 as diff_v
         |  from
         |  (
         |    select *,lag(ts,1,0) over(partition by id order by ts) as ts2
         |    from test1
         |  )t1
         |)tt1
         |""".stripMargin).show()

  }

  def analysis6(spark:SparkSession): Unit ={
    val data = Seq(
      T5(1001,"2021-12-12"),
      T5(1002,"2021-12-12"),
      T5(1001,"2021-12-13"),
      T5(1001,"2021-12-14"),
      T5(1001,"2021-12-16"),
      T5(1002,"2021-12-16"),
      T5(1001,"2021-12-19"),
      T5(1002,"2021-12-17"),
      T5(1001,"2021-12-20")
    )
    import spark.implicits._

    val df = data.toDF()
    df.createOrReplaceTempView("test1")
    df.orderBy("id").show()
    spark.sql(
      s"""
         |select id,max(d) + 1 as max_login
         |from
         |(
         |  select id,flag,datediff(max(dt),min(dt)) as d
         |  from
         |  (
         |    select *,sum(if(diff_v > 2,1,0)) over(partition by id order by dt) as flag
         |    from
         |    (
         |      select *,datediff(dt,dt2) as diff_v
         |      from
         |      (
         |        select *,lag(dt,1,"1970-01-01") over(partition by id order by dt) as dt2
         |        from
         |        (
         |          select id,to_date(dt) as dt
         |          from test1
         |        )t1
         |      )tt1
         |    )ttt1
         |  )tttt1
         |  group by id,flag
         |)ttttt1
         |group by id
         |""".stripMargin).show()


  }

  def analysis7(spark:SparkSession): Unit ={
    val data = Seq(
      T6("oppo","2021-06-05","2021-06-09"),
      T6("oppo","2021-06-11","2021-06-21"),
      T6("vivo","2021-06-05","2021-06-15"),
      T6("vivo","2021-06-09","2021-06-21"),
      T6("redmi","2021-06-05","2021-06-21"),
      T6("redmi","2021-06-09","2021-06-15"),
      T6("redmi","2021-06-17","2021-06-26"),
      T6("huawei","2021-06-05","2021-06-26"),
      T6("huawei","2021-06-09","2021-06-15"),
      T6("huawei","2021-06-17","2021-06-21")
    )
    import spark.implicits._

    val df: DataFrame = data.toDF()
    df.createOrReplaceTempView("test1")

    spark.sql(
      s"""
         |select id,sum(if(diff_date < 0,0,diff_date+1)) as d
         |from
         |(
         |  select *,datediff(edt,stt2) as diff_date
         |  from
         |  (
         |    select *,if(new_stt is null,stt,if(new_stt < stt,stt,date_add(new_stt,1))) as stt2
         |    from
         |    (
         |      select *,max(edt) over(partition by id order by stt rows between unbounded preceding and 1 preceding) as new_stt
         |      from
         |      (
         |        select id,to_date(stt) as stt,to_date(edt) as edt
         |        from test1
         |      )t1
         |    )tt1
         |  )ttt1
         |)tttt1
         |group by id
         |""".stripMargin).show()

    spark.sql(
      s"""
         |select id,sum(if(diff_date < 0,0,diff_date)) as s
         |from
         |(
         |  select *,case when new_stt is null then datediff(edt,stt) else datediff(edt,date_add(new_stt,1)) end as diff_date
         |  from
         |  (
         |    select *,max(edt) over(partition by id order by stt rows between unbounded preceding and 1 preceding) as new_stt
         |    from
         |    (
         |      select id,to_date(stt) as stt,to_date(edt) as edt
         |      from test1
         |    )t1
         |  )tt1
         |)ttt1
         |group by id
         |""".stripMargin).show()
  }

  def analysis8(spark:SparkSession): Unit ={
    val data = Seq(
      T7(1001, "2021-06-14 12:12:12", "2021-06-14 18:12:12"),
      T7(1003, "2021-06-14 13:12:12", "2021-06-14 16:12:12"),
      T7(1004, "2021-06-14 13:15:12", "2021-06-14 20:12:12"),
      T7(1002, "2021-06-14 15:12:12", "2021-06-14 16:12:12"),
      T7(1005, "2021-06-14 15:18:12", "2021-06-14 20:12:12"),
      T7(1001, "2021-06-14 20:12:12", "2021-06-14 23:12:12"),
      T7(1006, "2021-06-14 21:12:12", "2021-06-14 23:15:12"),
      T7(1007, "2021-06-14 22:12:12", "2021-06-14 23:10:12")
    )
    import spark.implicits._
    val df = data.toDF()
    df.createOrReplaceTempView("test1")

    spark.sql(
      s"""
         |select max(sum_id) as max_data
         |from
         |(
         |  select *,sum(p) over(order by dt) as sum_id
         |  from
         |  (
         |    select id,stt as dt,1 as p from test1
         |    union
         |    select id,edt as dt,-1 as p from test1
         |  )t1
         |)tt1
         |""".stripMargin).show()

  }

  /**
   * 问题1:多行转多列
   * @param spark
   */
  def analysis9(spark:SparkSession): Unit ={
    val data = Seq(
      T8("2014","B","9"),
      T8("2015","A","8"),
      T8("2014","A","10"),
      T8("2015","B","7")
    )
    import spark.implicits._
    val df = data.toDF()
    df.createOrReplaceTempView("test1")
    /**
     * 问题一：多行转多列
     * 问题描述：将上述表内容转为如下输出结果所示：
     * a  col_A col_B
     * 2014  10   9
     * 2015  8    7
     */

    spark.sql(
      s"""
         |select a,
         |max(case when b = 'A' then c end) as col_A,
         |max(case when b = 'B' then c end) as col_B
         |from test1
         |group by a
         |""".stripMargin).createOrReplaceTempView("test2")

    /**
     * 问题二：如何将结果转成源表？（多列转多行）
     * 问题描述：将问题一的结果转成源表，问题一结果表名为t1_2。
     *
     */

    spark.sql(
      s"""
         |select a,'a' as b,col_A as c
         |from test2
         |union
         |select a,'b' as b,col_B as c
         |from test2
         |""".stripMargin).show()

    /**
     * 问题三：同一部门会有多个绩效，求多行转多列结果
     * 问题描述：2014年公司组织架构调整，导致部门出现多个绩效，业务及人员不同，无法合并算绩效，源表内容如下：
     */

    val data1 = Seq(
      T8("2014","B","9"),
      T8("2015","A","8"),
      T8("2014","A","10"),
      T8("2015","B","7"),
      T8("2015","B","6")
    )
    val df1 = data1.toDF()
    df1.createOrReplaceTempView("test2")
    val frame = spark.sql(
      s"""
         |select a,max(case when b = 'A' then c end) as col_A,
         |max(case when b = 'B' then c end) as col_B
         |from
         |(
         |  select a,b,concat_ws(',',collect_list(c)) as c
         |  from test2
         |  group by a,b
         |)t1
         |group by a
         |""".stripMargin)
    frame.show()
    frame.createOrReplaceTempView("test3")

    spark.sql(
      s"""
         |select a,'b' as b,explode(split(col_B,',')) as c
         |from test3
         |union
         |select a,'c' as b,explode(split(col_A,',')) as c
         |from test3
         |""".stripMargin).show()
  }

  def analysis10(spark:SparkSession): Unit ={
    val data = Seq(
      T8("2014","A","3"),
      T8("2014","B","1"),
      T8("2014","C","2"),
      T8("2015","A","4"),
      T8("2015","D","3")
    )
    import spark.implicits._
    val df = data.toDF()
    df.createOrReplaceTempView("test1")

    /**
     * 问题一：按a分组取b字段最小时对应的c字段
     * 输出结果如下所示：
     */
    spark.sql(
      s"""
         |select t1.*
         |from test1 t1
         |join
         |(
         |  select a,min(b) as min_b
         |  from test1
         |  group by a
         |)t2
         |on t1.a = t2.a and t1.b = t2.min_b
         |""".stripMargin).show()

    spark.sql(
      s"""
         |select *
         |from
         |(
         |  select *,row_number() over(partition by a order by b) as rn
         |  from test1
         |)t1
         |where rn = 1
         |""".stripMargin).show()
  }

  def analysis11(spark:SparkSession): Unit ={
    val data = Seq(
      TT2("1001","2021-12-12"),
      TT2("1002","2021-12-12"),
      TT2("1001","2021-12-13"),
      TT2("1001","2021-12-14"),
      TT2("1001","2021-12-16"),
      TT2("1002","2021-12-16"),
      TT2("1001","2021-12-19"),
      TT2("1002","2021-12-17"),
      TT2("1001","2021-12-20")
    )
    import spark.implicits._
    data.toDF().createOrReplaceTempView("test1")


    spark.sql(
      s"""
         |select *,row_number() over(partition by id order by dt) as rn1,
         |row_number() over(partition by id,flag order by dt) as rn2
         |from
         |(
         |    select *,sum(if(dt_diff > 2, 1,0)) over(partition by id order by dt) as flag
         |    from
         |    (
         |      select *,datediff(dt,dt2) as dt_diff
         |      from
         |      (
         |        select *,lag(dt,1) over(partition by id order by dt) as dt2
         |        from test1
         |      )t1
         |    )tt1
         |)ttt1
         |
         |""".stripMargin).show()

    spark.sql(
      s"""
         |select *,rn1 - rn2 as flag2
         |from
         |(
         |  select *,row_number() over(partition by id order by dt) as rn1,
         |  row_number() over(partition by id,flag order by dt) as rn2
         |  from
         |  (
         |    select *,sum(if(dt_diff <= 2,0,1)) over(partition by id order by dt) as flag
         |    from
         |    (
         |      select *,datediff(dt,dt2) as dt_diff
         |      from
         |      (
         |        select *,lag(dt,1) over(partition by id order by dt) as dt2
         |        from test1
         |      )t1
         |    )tt1
         |  )ttt1
         |)tttt1
         |
         |""".stripMargin).show()


    spark.sql(
      s"""
         |select id
         |from
         |(
         |  select *,rn1 - rn2 as flag2
         |  from
         |  (
         |    select *,row_number() over(partition by id order by dt) as rn1,
         |    row_number() over(partition by id,flag order by dt) as rn2
         |    from
         |    (
         |      select *,sum(if(dt_diff <= 2,0,1)) over(partition by id order by dt) as flag
         |      from
         |      (
         |        select *,datediff(dt,dt2) as dt_diff
         |        from
         |        (
         |          select *,lag(dt,1) over(partition by id order by dt) as dt2
         |          from test1
         |        )t1
         |      )tt1
         |    )ttt1
         |  )tttt1
         |)a
         |group by id,flag2
         |having count(1) >= 4
         |""".stripMargin).show()



  }
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test")
    conf.setMaster("local[*]")
    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")

    analysis11(spark)

  }

  case class TT2(id: String, dt: String)


}























