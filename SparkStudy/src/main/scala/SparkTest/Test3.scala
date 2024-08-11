package SparkTest

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
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

  /**
   * 4、创建一个 4 个分区的 RDD数据为Array(10,20,30,40,50,60)，使用glom将每个分区的数据放到一个数组
   */
  def practice4(): Unit = {
    println("=====practice4=====")
    var rdd1: RDD[Int] = sc.makeRDD(Array(10, 20, 30, 40, 50, 60), 4)
    val rdd2: RDD[Array[Int]] = rdd1.glom()
    rdd2.foreach(x => println(x.mkString("Array()")))

  }

  /**
   * 5、创建一个 RDD数据为Array(1, 3, 4, 20, 4, 5, 8)，按照元素的奇偶性进行分组
   */
  def practice5(): Unit = {
    println("=====practice5=====")
    val rdd1: RDD[(String,Int)] = sc.makeRDD(Array(("a",1), ("b",2), ("c",3), ("a",20), ("b",4), ("b",5), ("c",8)))
    val rdd2 = rdd1.groupBy(_._1)
    rdd2.foreach(x => println(x))

  }

  /**
   * 创建一个 RDD（由字符串组成）Array(“xiaoli”, “laoli”, “laowang”, “xiaocang”, “xiaojing”, “xiaokong”)，过滤出一个新 RDD（包含“xiao”子串）
   */
  def practice6(): Unit = {
    println("=====practice6=====")
    val rdd1 = sc.makeRDD(Array("xiaoli", "laoli", "laowang", "xiaocang", "xiaojing", "xiaokong"))
    val rdd2 = rdd1.filter(_.contains("xiaoli"))
    rdd2.foreach(println)

  }

  /**
   * 7、创建一个 RDD数据为1 to 10，请使用sample不放回抽样
   */
  def practice7(): Unit = {
    println("=====practice7=====")
    val rdd1 = sc.parallelize(1 to 10)
    val rdd2: RDD[Int] = rdd1.sample(withReplacement = false, 0.5)
    rdd2.foreach(println)
  }

  /**
   * 创建一个 RDD数据为 1 to 10，请使用sample放回抽样
   */
  def practice8(): Unit = {
    val inputRdd: RDD[Int] = sc.parallelize(1 to 10)
    val value: RDD[Int] = inputRdd.sample(withReplacement = true, 0.5)
    value.foreach(println)
  }

  def practice9(): Unit = {
    val inputRdd: RDD[Int] = sc.parallelize(Array(10, 10, 2, 5, 3, 5, 3, 6, 9, 1))
    inputRdd.foreach(println)
    println("=======================")
    val value = inputRdd.distinct()
    value.foreach(println)
  }

  def practice10(): Unit = {
    val inputRdd: RDD[Int] = sc.parallelize(0 to 100,5)
    val value = inputRdd.repartition(2)
    value.foreach(println)

  }

  /**
   * 创建一个 RDD数据为1,3,4,10,4,6,9,20,30,16,请给RDD进行分别进行升序和降序排列
   */
  def practice11(): Unit = {
    val inputRdd = sc.parallelize(Seq(1, 3, 4, 10, 4, 6, 9, 20, 30, 16), 1)
    inputRdd.sortBy(x => x).foreach(println(_))
    println("====================")
    inputRdd.sortBy(x => x,ascending = false).foreach(println)
  }

  /**
   * 创建两个RDD，分别为rdd1和rdd2数据分别为1 to 6和4 to 10，求并集
   */
  def practice12(): Unit = {
    val rdd1 = sc.parallelize(1 to 6,6)
    val rdd2 = sc.parallelize(4 to 10,3)
    println("=========并集==========")
    rdd1.intersection(rdd2).foreach(println)
    println("=========差集rdd1 -> rdd2==========")
    rdd1.subtract(rdd2,2).foreach(println)
    println("=========差集rdd2 -> rdd1==========")
    rdd2.subtract(rdd1,1).foreach(println)


  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    practice12()
  }


}
