package SparkTest

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
/**
 * @ClassName: TestAddDate
 * @Auther: SDY
 * @Description:
 * @Date: 2024/9/16 19:35
 * */
object TestAddDate {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("testAdd")
    conf.setMaster("local[*]")

    val spark = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")

    val rdd: RDD[(String, String, Int)] = spark.sparkContext.parallelize(Seq(
      ("2024-01-01", "a", 100),
      ("2024-01-02", "a", 80),
      ("2024-01-04", "a", 60),
      ("2024-01-06", "a", 40),
      ("2024-01-10", "a", 10)
    ))
    import spark.implicits._
    rdd.toDF("dt","uid","amt").createTempView("deal")
    println("========原始数据=========")
//    spark.sql(
//      s"""
//         |select *
//         |from deal
//         |""".stripMargin).show(false)
    // 求最小和最大日期
    val minMaxDate = spark.sql(
      s"""
         |select
         |uid,min(dt) as start_date,max(dt) as end_date
         |from deal
         |group by uid
         |""".stripMargin)


    println("========最大最小日期=========")
    minMaxDate.show(false)
    minMaxDate.createTempView("deal_min_max_dt")

    spark.sql(
      s"""
         |select posexplode(split(space(datediff(end_date,start_date)),''))
         |from deal_min_max_dt
         |""".stripMargin).show(false)


    // 补全缺失的日期
    println("========补缺缺失日期=========")
    val date_addition = spark.sql(
      s"""
         |select uid,date_add(start_date,pos) dt,pos,val
         |from deal_min_max_dt
         |lateral view posexplode(split(space(datediff(end_date,start_date)),'')) t as pos,val
         |""".stripMargin)
    date_addition.show(false)
    date_addition.createTempView("date_addition")

    // 分组内排序 取最后一行的值 补全金额数据
    val day_amt = spark.sql(
      s"""
         |select
         |t1.dt,
         |t1.uid,
         |t2.amt,
         |coalesce(t2.amt,0) as amt_nvl,
         |last_value(t2.amt,false) over(partition by t1.uid order by t1.dt) as amo,
         |last_value(t2.amt,true) over(partition by t1.uid order by t1.dt) as amount,
         |last_value(t2.amt,true) over(partition by t1.uid order by t1.dt desc) as amount2,
         |row_number() over(partition by t1.uid order by t1.dt) as rn
         |from date_addition t1
         |left join deal t2
         |on t1.dt = t2.dt
         |order by t1.dt
         |""".stripMargin)

    println("========分组内排序=========")
    day_amt.show(false)
    day_amt.createTempView("day_amt")

    spark.sql(
      s"""
         |
         |select
         |uid,dt,amt,amt_nvl,amount,amount2,
         |round(avg(amount) over(partition by uid order by dt),2) as avg_amount,
         |round(sum(amount) over(partition by uid order by dt)/count(dt) over(partition by uid order by dt),2) as avg_amount2,
         |sum(amount) over(partition by uid order by dt) as sum_amount,
         |count(dt) over(partition by uid order by dt) as cnt_dt
         |from day_amt
         |""".stripMargin).show(false)





  }

}
