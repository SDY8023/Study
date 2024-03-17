package SparkTest

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author 18438
 * @date 2024/3/17 16:38
 * @description
 */
object Test3 {
  val conf = new SparkConf()
  conf.setMaster("local[*]")
  conf.setAppName("test")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    practice3()
  }

  /**
   * 创建一个1-10数组的RDD，将所有元素*2形成新的RDD
   */
  def practice1(): Unit = {
    println("=====practice1=====")
    val rdd1 = sc.parallelize(1 to 10)
    val rdd2 = rdd1.map(_ * 2)
    rdd2.foreach(println)
  }

  /**
   * 2、创建一个10-20数组的RDD，使用mapPartitions将所有元素*2形成新的RDD
   */
  def practice2(): Unit = {
    println("=====practice2=====")
    val rdd1 = sc.parallelize(10 to 20)
    val rdd2 = rdd1.mapPartitions(x => x.map(_ * 2))
    rdd2.foreach(println)
  }

  /**
   * 3、创建一个元素为 1-5 的RDD，运用 flatMap创建一个新的 RDD，新的 RDD 为原 RDD 每个元素的 平方和三次方 来组成 1,1,4,8,9,27…
   */
  def practice3(): Unit = {
    println("=====practice3=====")
    val rdd1 = sc.parallelize(1 to 10,1)
    rdd1.flatMap(x => {
      List(Math.pow(x,2).toInt,Math.pow(x,3).toInt)
    })foreach(println)

  }



}
