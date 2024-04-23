package UserBehaviorAnalysis.MarketAnalysis

//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

import UserBehaviorAnalysis.MarketAnalysis.bean.MarketingUserBehavior
import UserBehaviorAnalysis.MarketAnalysis.function.MarketingCountTotal
import UserBehaviorAnalysis.MarketAnalysis.source.SimulateEventSource
import com.sun.org.glassfish.external.statistics.TimeStatistic
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time


/**
 * @ClassName: AppMarketingStatistics
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:21
 * */
object AppMarketingStatistics {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val dataSource: DataStream[MarketingUserBehavior] = env.addSource(new SimulateEventSource(Long.MaxValue))
      .assignAscendingTimestamps(_.timestamp)

    dataSource.filter(_.behavior != "UNINSTALL")
      .map(data => {
        ("dummyKey",1L)
      })
      .keyBy(_._1)
      .timeWindow(Time.hours(1),Time.seconds(10))
      .process(new MarketingCountTotal())
      .print()

    env.execute(getClass.getSimpleName)
  }

}
