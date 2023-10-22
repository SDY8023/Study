package SparkTest

import CaseClasses.ParametricData
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf}
import org.apache.spark.sql.{KeyValueGroupedDataset, SparkSession}
/**
 * @ClassName: Test
 * @Auther: SDY
 * @Description:
 * @Date: 2023/10/15 09:42
 * */
object Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test1")
//    conf.setMaster("local[*]")
    val spark = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()
    import spark.sqlContext.implicits._

    val sc = spark.sparkContext
    val rdd1 = sc.textFile("/user/hive/warehouse/test.db/dwd_fact_ft_stdf_parametric_data/lot=test1/guid=guid1/create1.txt",10)
    val rdd2: RDD[ParametricData] = rdd1.map(_.split("|")).map(x => ParametricData(x(0).toInt, x(1), x(2), x(3), x(4), x(5), x(6)
      , x(7), x(8), x(9), x(10), x(11), x(12), x(13)))
    val ds1 = rdd2.toDS()
    println("==================="+rdd2.getNumPartitions)

    ds1.groupByKey(_.id % 10)


  }

}
