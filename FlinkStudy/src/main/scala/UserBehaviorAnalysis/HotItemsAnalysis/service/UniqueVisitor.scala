package UserBehaviorAnalysis.HotItemsAnalysis.service

import UserBehaviorAnalysis.DataSource.DataSourceFrom
import UserBehaviorAnalysis.HotItemsAnalysis.bean.UserBehavior
import UserBehaviorAnalysis.HotItemsAnalysis.function.UvCountByWindow
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @ClassName: UniqueVisitor
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/10 21:49
 * */
object UniqueVisitor {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)
    // 读数据源
    val dataSource: DataStream[UserBehavior] = env.addSource(DataSourceFrom.dataFromKafka())
      .map(data => {
        val dataArray = data.split(",")
        UserBehavior(dataArray(0).toLong,
          dataArray(1).toLong,
          dataArray(2).toInt,
          dataArray(3),
          dataArray(4).toLong)
      }).assignAscendingTimestamps(_.timestamp * 1000)

    dataSource.filter(_.behavior == "pv")
      .timeWindowAll(Time.seconds(60 * 60))
      .apply(new UvCountByWindow())
      .print()

    env.execute()
  }

}
