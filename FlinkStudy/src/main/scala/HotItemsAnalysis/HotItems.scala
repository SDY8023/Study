package HotItemsAnalysis

import HotItemsAnalysis.bean.{CountAgg, TopNHotItems, WindowResultFunction}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala._
/**
 * @ClassName: HotItems
 * @Auther: SDY
 * @Description:
 * @Date: 2023/12/17 15:36
 * */
case class UserBehavior(userId:Long,itemId:Long,categoryId:Int,behavior:String,timestamp:Long)
case class ItemViewCount(itemId:Long,windowEnd:Long,count:Long)
object HotItems {
  def main(args: Array[String]): Unit = {
    // 创建流执行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // 设定Time类型为eventTime
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    // 设置并发度
    env.setParallelism(1)
    // 计算指标
    val stream = env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\UserBehavior.csv")
      .map(line => {
        val lineArray = line.split(",")
        UserBehavior(lineArray(0).toLong, lineArray(1).toLong, lineArray(2).toInt, lineArray(3), lineArray(4).toLong)
      }).assignAscendingTimestamps(_.timestamp * 1000)

    val windowStream: DataStream[ItemViewCount] = stream.filter(_.behavior == "pv")
      .keyBy("itemId") // 返回的是KeyedStream[T,JavaTuple],key的类型是JavaTuple
      .timeWindow(Time.minutes(60), Time.minutes(5))
      .aggregate(new CountAgg(), new WindowResultFunction())

    windowStream.keyBy("windowEnd")
      .process(new TopNHotItems(3))
      .print()

    env.execute("Hot Items Job")
  }

}
