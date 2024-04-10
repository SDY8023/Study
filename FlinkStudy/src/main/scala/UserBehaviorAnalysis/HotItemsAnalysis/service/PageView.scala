package UserBehaviorAnalysis.HotItemsAnalysis.service

import UserBehaviorAnalysis.DataSource.DataSourceFrom
import UserBehaviorAnalysis.HotItemsAnalysis.bean.UserBehavior
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @ClassName: PageView
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/10 21:33
 * */
object PageView {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 设置事件时间
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    // 设置并行度为1
    env.setParallelism(1)
    // 读取数据
    val dataSource: DataStream[UserBehavior] = env.addSource(DataSourceFrom.dataFromKafka())
      .map(line => {
        val dataArray = line.split(",")
        UserBehavior(dataArray(0).toLong,
          dataArray(1).toLong,
          dataArray(2).toInt,
          dataArray(3),
          dataArray(4).toLong)
      }).assignAscendingTimestamps(_.timestamp * 1000) // 指定哪个字段作为eventTime

    dataSource.filter(_.behavior == "pv")
      .map(x => ("pv",1))
      .keyBy(_._1)
      .timeWindow(Time.seconds(60))
      .sum(1)
      .print()
    env.execute("Page View Job")

  }

}
