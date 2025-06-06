package PageAdverAnalysis

import PageAdverAnalysis.bean.{AdClickLog, BlackListWarning}
import PageAdverAnalysis.function.{CountAgg, CountResult, FilterBlackListUser}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @ClassName: AdStatisticeByGeoBlack
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 22:06
 * */
object AdStatisticsByGeoBlack {
  val blackListOutputTag = new OutputTag[BlackListWarning]("blacklist")
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val dataResource: DataStream[AdClickLog] = env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\AdClickLog.csv")
      .map(line => {
        val dataArray: Array[String] = line.split(",")
        AdClickLog(dataArray(0).toLong, dataArray(1).toLong, dataArray(2), dataArray(3),dataArray(4).toLong)
      }).assignAscendingTimestamps(_.timeStamp)

    val filterBlackListStream = dataResource.keyBy(logData => (logData.userId, logData.adId))
      .process(new FilterBlackListUser(100))
    filterBlackListStream
      .keyBy(_.province)
      .timeWindow(Time.hours(1),Time.seconds(5))
      .aggregate(new CountAgg(),new CountResult)
      .print()
    filterBlackListStream.getSideOutput(blackListOutputTag)
      .print("black list")

    env.execute("ad statistics job")

  }

}
