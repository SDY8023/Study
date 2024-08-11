package SparkCoreTest

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.apache.spark.sql.SparkSession

/**
 * @ClassName: Test1
 * @Auther: SDY
 * @Description:
 * @Date: 2024/8/6 21:16
 * */
object Test1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("Test1")
    val sc = new SparkContext(conf)
    test6(sc)
  }

  /**
   * 创建一个1-10数组的RDD，将所有元素*2形成新的RDD
   * @param sc
   */
  def test1(sc:SparkContext): Unit ={
    val inputRdd: RDD[Int] = sc.parallelize(1 to 10,2)
    inputRdd.map(_* 2).foreach(println)
  }

  /**
   * 创建一个10-20数组的RDD，使用mapPartitions将所有元素*2形成新的RDD
   * @param sc
   */
  def test2(sc:SparkContext): Unit ={
    val inputRdd = sc.parallelize(10 to 20,3)
    inputRdd.mapPartitions(iter => {
      val partitionId = TaskContext.getPartitionId()
      if(partitionId % 2 != 0){
        iter.map(_ * 2)
      }else{
        iter.map(_ * 3)
      }

    },preservesPartitioning = true).foreach(println(_))
  }

  /**
   * 创建一个元素为 1-5 的RDD，运用 flatMap创建一个新的 RDD，新的 RDD 为原 RDD 每个元素的 平方和三次方 来组成 1,1,4,8,9,27…
   * @param sc
   */
  def test3(sc:SparkContext): Unit ={
    val inputRdd = sc.parallelize(1 to 5)
    inputRdd.foreach(println(_))
    println("======================")
    inputRdd.flatMap(n => {
      List(Math.pow(n,2).toInt,Math.pow(n,3).toInt)
    }).foreach(println(_))
  }

  /**
   * 创建一个 4 个分区的 RDD数据为Array(10,20,30,40,50,60)，使用glom将每个分区的数据放到一个数组
   * @param sc
   */
  def test4(sc:SparkContext): Unit ={
    val inputRdd: RDD[Int] = sc.makeRDD(Array(10, 20, 30, 40, 50, 60), 1)
    // glom算子会将每个分区的数据放在一个数组中，组成一个RDD，也就是说原来的RDD有多少个分区，glom之后的RDD就会有多少个元素，每个元素是一个数组
    val value: RDD[Array[Int]] = inputRdd.glom()
    value.foreach(x => println(x.mkString(",")))
  }

  /**
   * 创建一个 RDD数据为Array(1, 3, 4, 20, 4, 5, 8)，按照元素的奇偶性进行分组
   * @param sc
   */
  def test5(sc:SparkContext): Unit ={
    val inputRdd = sc.makeRDD(Array(1, 3, 4, 20, 4, 5, 8))
    val value: RDD[(Boolean, Iterable[Int])] = inputRdd.groupBy(x => x % 2 == 0)
    value.foreach(println(_))
    value.map(x => {
      (x._1,x._2.max)
    }).foreach(println)
  }

  /**
   * 创建一个 RDD（由字符串组成）Array(“xiaoli”, “laoli”, “laowang”, “xiaocang”, “xiaojing”, “xiaokong”)，
   * 过滤出一个新 RDD（包含“xiao”子串）
   * @param sc
   */
  def test6(sc:SparkContext): Unit ={
    val inputRdd: RDD[String] = sc.makeRDD(Array("xiaoli", "laoli", "laowang", "xiaocang", "xiaojing", "xiaokong"))
    inputRdd.filter(_.contains("xiao")).foreach(println(_))
  }

}
