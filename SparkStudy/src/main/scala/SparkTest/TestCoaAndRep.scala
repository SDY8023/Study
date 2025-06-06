package SparkTest

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @ClassName: TestCoaAndrep
 * @Auther: SDY
 * @Description:
 * @Date: 2024/8/11 14:14
 * */
object TestCoaAndRep {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("Test1")
    val spark = new SparkContext(conf)
    var filePath = args(0)
    val inputRdd = spark.textFile(filePath + "5Gcontent.txt").repartition(100)
    inputRdd.saveAsTextFile("/test/sparkTestData/test1.txt")
  }

}
