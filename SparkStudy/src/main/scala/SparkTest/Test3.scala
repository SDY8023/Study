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
   * 创建一个 RDD（由字符串组成）Array("xiaoli", "laoli", "laowang", "xiaocang", "xiaojing", "xiaokong")，过滤出一个新 RDD（包含"xiao"子串）
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
    println("=========交集rdd2 rdd1==========")
    rdd1.union(rdd2).foreach(println)
    println("=========笛卡尔集rdd2 rdd1==========")
    val value: RDD[(Int, Int)] = rdd1.cartesian(rdd2)
    value.sortBy(_._1).foreach(println)
  }

  /**
   * 创建两个RDD，分别为rdd1和rdd2数据分别为1 to 5和11 to 15，对两个RDD拉链操作
   * (1,2,3,4,5)
   * (11,12,13,14,15)
   * 拉链操作后
   * ((1,11),(2,12),(3,13),(4,14),(5,15))
   * 拉链操作必须两个rdd的元素数量相同
   */
  def practice17(): Unit ={
    val rdd1 = sc.parallelize(1 to 5)
    val rdd2 = sc.parallelize(11 to 15)
    val value: RDD[(Int, Int)] = rdd1.zip(rdd2)
    value.foreach(println)
  }

  /**
   * 创建一个RDD数据为List((“female”,1),(“male”,5),(“female”,5),(“male”,2))，请计算出female和male的总数分别为多少
   */
  def practice18(): Unit ={
    val rdd = sc.parallelize(List(("female", 1), ("male", 5), ("female", 5), ("male", 2)))
    rdd.reduceByKey(_+_).foreach(println)
  }

  /**
   * 创建一个RDD数据为List(("female",1),("male",5),("female",5),("male",2))，请计算出female和male的总数分别为多少
   */
  def practice19(): Unit ={
    val rdd1 = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)),2)
    rdd1.foreachPartition(x => {
      val list = x.toList
      println(s"partition ${list}")
    })

    println("========aggregateByKey=============")
    rdd1.aggregateByKey(0)((tmp,item) => {
      println(tmp,item,"---")
      Math.max(tmp,item)
    },
      (tmp,result) => {
        println(tmp,result,"---")
        tmp + result
    }
    ).foreach(println)

    println("=========groupByKey=========")
    rdd1.groupBy(_._1)
      .foreach(x => {
        println(s"k:${x._1} v:${x._2.toList.minBy(_._2)}")
      })
  }


  /**
   * 创建一个有两个分区的 pairRDD数据为Array((“a”, 88), (“b”, 95), (“a”, 91), (“b”, 93), (“a”, 95), (“b”, 98))，根据 key 计算每种 key 的value的平均值
   */
  def practice20(): Unit ={
    val pairRDD = sc.parallelize(Array(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)), 2)
    pairRDD.groupByKey()
      .map(x => {
        println(s"k:${x._1}  v:${x._2.sum / x._2.size}")
      }).foreach(println)
  }


  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    practice20()
  }


}
