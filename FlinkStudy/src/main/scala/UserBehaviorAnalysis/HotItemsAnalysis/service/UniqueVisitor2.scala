package UserBehaviorAnalysis.HotItemsAnalysis.service

import UserBehaviorAnalysis.DataSource.DataSourceFrom
import UserBehaviorAnalysis.HotItemsAnalysis.bean.UserBehavior
import UserBehaviorAnalysis.HotItemsAnalysis.function.{MyTrigger, UvCountWithBloom}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @ClassName: UniqueVisitor2
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/17 20:53
 * */
object UniqueVisitor2 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val dataSource: DataStream[UserBehavior] = env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\UserBehavior.csv")
      .map(data => {
        val dataArray = data.split(",")
        UserBehavior(dataArray(0).toLong,
          dataArray(1).toLong,
          dataArray(2).toInt,
          dataArray(3),
          dataArray(4).toLong)
      }).assignAscendingTimestamps(_.timestamp * 1000)

    dataSource.filter(_.behavior == "pv")
      .map(data => ("dummykey",data.userId))
      .keyBy(_._1)
      .timeWindow(Time.seconds(60 * 60))
      .trigger(new MyTrigger()) // 自定义窗口触发规则
      .process(new UvCountWithBloom())
      .print("aa")
    env.execute("Unique Visitor with bloom Job")

  }

}
