package PageAdverAnalysis

import PageAdverAnalysis.bean.AdClickLog
import PageAdverAnalysis.function.{CountAgg, CountResult}
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, KeyedStream, StreamExecutionEnvironment, WindowedStream}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow

/**
 * @ClassName: AdStatisticeByGeo
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:43
 * */
object AdStatisticsByGeo {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val dataResource: DataStream[AdClickLog] = env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\AdClickLog.csv")
      .map(line => {
        val dataArray: Array[String] = line.split(",")
        AdClickLog(dataArray(0).toLong, dataArray(1).toLong, dataArray(2), dataArray(3),dataArray(4).toLong)
      }).assignAscendingTimestamps(_.timeStamp)

    val kStream: KeyedStream[AdClickLog, String] = dataResource.keyBy(_.province)
    val wStream: WindowedStream[AdClickLog, String, TimeWindow] = kStream.timeWindow(Time.hours(1), Time.seconds(5))
    wStream.aggregate(new CountAgg(),new CountResult()).print()

    env.execute("ad statistics job")


  }

}
