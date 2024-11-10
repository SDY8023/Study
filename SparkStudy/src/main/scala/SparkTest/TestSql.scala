package SparkTest

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author 18438
 * @date 2024/11/10 14:17
 * @description
 */
case class T1(a:String,b:String,c:String)
object TestSql {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("TestSql")
    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    analysis1(spark)

  }

  def analysis1(spark:SparkSession): Unit = {
    val data = Seq(
      T1("2014", "A", "3"),
      T1("2014", "B", "1"),
      T1("2014", "C", "2"),
      T1("2015", "A", "4"),
      T1("2015", "D", "3")
    )

    import spark.implicits._
    val df1 = data.toDF()
    df1.createOrReplaceTempView("test1")
    spark.sql(
      s"""
         |select *,row_number() over(partition by a order by b) as rn
         |from test1
         |
         |""".stripMargin).show()

  }

}
