package UserBehaviorAnalysis.MarketAnalysis

import UserBehaviorAnalysis.MarketAnalysis.bean.MarketingUserBehavior
import UserBehaviorAnalysis.MarketAnalysis.function.MarketingCountByChannel
import UserBehaviorAnalysis.MarketAnalysis.source.SimulateEventSource
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @ClassName: AppMarketingByChannel
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/20 20:09
 * */
object AppMarketingByChannel {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val stream: DataStream[MarketingUserBehavior] = env.addSource(new SimulateEventSource(1000000000))
      .assignAscendingTimestamps(_.timestamp)

    stream.filter(_.behavior != "UNINSTALL")
      .map(data => {
        ((data.channel,data.behavior),1L)
      }).keyBy(_._1)
      .timeWindow(Time.hours(1),Time.seconds(1))
      .process(new MarketingCountByChannel())
      .print()
    env.execute(getClass.getSimpleName)

  }

}
