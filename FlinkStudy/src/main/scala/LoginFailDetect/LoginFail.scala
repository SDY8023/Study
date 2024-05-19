package LoginFailDetect

import LoginFailDetect.bean.{LoginEvent, LoginFailCaseClass}
import LoginFailDetect.function.MatchFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @ClassName: LoginFail
 * @Auther: SDY
 * @Description:
 * @Date: 2024/5/19 18:21
 * */
object LoginFail {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\LoginLog.csv")
      .map(data => {
        val dataArray: Array[String] = data.split(",")
        LoginEvent(dataArray(0).toLong,dataArray(1),dataArray(2),dataArray(3).toLong)
      }).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[LoginEvent](Time.milliseconds(30000)) {
      override def extractTimestamp(element: LoginEvent): Long = {
        element.eventTime * 1000L
      }
    }).keyBy(_.userId)
      .process(new MatchFunction())
      .print()

    env.execute("Login Fail Detect Job")
  }

}
