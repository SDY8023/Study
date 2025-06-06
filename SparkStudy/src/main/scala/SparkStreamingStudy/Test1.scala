package SparkStreamingStudy

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream.toPairDStreamFunctions
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @ClassName: Test1
 * @Auther: SDY
 * @Description:
 * @Date: 2023/9/24 16:28
 * */
object Test1 {
  def main(args: Array[String]): Unit = {

    // 初始化配置信息
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("StreamWordCount")
    // 初始化sparkStreamingContext
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))

    val dataStream: ReceiverInputDStream[String] = ssc.socketTextStream("192.168.10.101", 9999)
    // 将每行数据切分开
    val wordCountStream: DStream[(String, Int)] = dataStream.flatMap(_.split(" ")).map((_, 1))
    val wordStream: DStream[(String, Int)] = wordCountStream.reduceByKeyAndWindow((x:Int, y:Int) => (x + y), Seconds(12), Seconds(6))

    wordStream.print()
    // 启动Stream
    ssc.start()
    // 确保程序不会提前退出
    ssc.awaitTermination()

  }

}
