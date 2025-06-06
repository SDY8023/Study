package SparkTest

import org.apache.spark.{SPARK_BRANCH, SparkConf}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * @author 18438
 * @date 2024/11/2 12:41
 * @description
 */
object Test5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test5")
    conf.setMaster("local[*]")

    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val rdd: RDD[(String, String, Int)] = spark.sparkContext.parallelize(Seq(
      ("1","20170101",100),
      ("3","20170101",20),
      ("4","20170101",30),
      ("1","20170102",200),
      ("2","20170102",240),
      ("3","20170102",30),
      ("4","20170102",2),
      ("1","20170103",300),
      ("2","20170103",250),
      ("3","20170103",30),
      ("4","20170103",260)
    ))
    import spark.implicits._
    val dataFrame: DataFrame = rdd.toDF("user_id", "month", "amt")
    dataFrame.createOrReplaceTempView("t")
    spark.sql(
      s"""
         |
         |select user_id,n1-n2,
         |count(1) as d
         |from
         |(
         |  select *,
         |  row_number() over(partition by user_id order by month) as n1,
         |  row_number() over(partition by user_id,flag order by month) as n2
         |  from
         |  (
         |    select *,
         |    case when rate_growth >= 0.5 then 1 else 0 end as flag
         |    from
         |    (
         |    	select
         |    	user_id,
         |    	month,
         |    	amt,
         |    	nvl((amt - lag(amt,1) over(partition by user_id order by month)) / (lag(amt,1) over(partition by user_id order by month)),0) as rate_growth
         |    	from t
         |    	order by user_id ,`month`
         |    )t1
         |  )t2
         |)t3
         |group by user_id,n1-n2
         |""".stripMargin).show()


  }

}
