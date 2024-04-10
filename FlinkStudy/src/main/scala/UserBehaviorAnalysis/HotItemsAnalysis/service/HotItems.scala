package UserBehaviorAnalysis.HotItemsAnalysis.service

import UserBehaviorAnalysis.DataSource.DataSourceFrom
import DataSourceFrom.dataFromKafka
import UserBehaviorAnalysis.HotItemsAnalysis.bean.{ItemViewCount, UserBehavior}
import UserBehaviorAnalysis.HotItemsAnalysis.function.{CountAgg, TopNHotItems, WindowResultFunction}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

import java.util.Properties

/**
 * @author 18438
 * @date 2024/4/6 14:09
 * @description
 */
object HotItems {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 设置时间为事件时间
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    // 控制输出不乱序
    env.setParallelism(4)
    // 读取数据源并进行转化
    // val resource = env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\UserBehavior.csv")
    val resource = env.addSource(dataFromKafka())
    val resourceMap = resource.map(l => {
      val lineArray = l.split(",")
      UserBehavior(lineArray(0).toLong, lineArray(1).toLong, lineArray(2).toInt, lineArray(3).toString, lineArray(4).toLong)
    })
      .assignAscendingTimestamps(_.timestamp * 1000) // 指定时间

    // 将数据按照itemId分类，计算每个窗口的中,每个商品浏览点击数
    val dataStream: DataStream[ItemViewCount] = resourceMap.filter(_.behavior == "pv")
      .keyBy("itemId")
      .timeWindow(Time.minutes(60), Time.minutes(5))
      .aggregate(new CountAgg(), new WindowResultFunction())

    // 根据windowEnd分组，计算topN
    dataStream.keyBy("windowEnd")
      .process(new TopNHotItems(3))
      .print()

    env.execute("Hot items job")

  }



}
