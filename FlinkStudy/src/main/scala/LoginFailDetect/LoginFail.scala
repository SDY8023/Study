package LoginFailDetect

import LoginFailDetect.bean.LoginEvent
import LoginFailDetect.function.MatchFunction
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author 18438
 * @date 2024/6/23 14:27
 * @description
 */
object LoginFail {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    // 读取文件数据
    env.readTextFile("D:\\File\\yang\\code\\idea\\Study\\FlinkStudy\\src\\main\\resources\\LoginLog.csv")
      .map(data => {
        val datas = data.split(",")
        LoginEvent(datas(0).toLong,datas(1),datas(2),datas(3).toLong)
      }).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[LoginEvent](Time.milliseconds(3000)) {
        override def extractTimestamp(t: LoginEvent): Long = {
          t.eventTime * 1000L
        }
      }).keyBy(_.userId)
      .process(new MatchFunction())
      .print()

    env.execute()

  }


}
