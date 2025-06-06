package LoginFailWithCEP

import LoginFailDetect.bean.LoginEvent
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.cep.scala.CEP
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author 18438
 * @date 2024/6/30 11:17
 * @description
 */
object LoginFailWithCep {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val dataSource: DataStream[LoginEvent] = env.readTextFile("D:\\File\\yang\\code\\idea\\Study\\FlinkStudy\\src\\main\\resources\\LoginLog.csv")
      .map(d => {
        val datas = d.split(",")
        LoginEvent(datas(0).toLong, datas(1), datas(2), datas(3).toLong)
      })
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[LoginEvent](Time.milliseconds(3000)) {
        override def extractTimestamp(t: LoginEvent): Long = {
          t.eventTime * 1000L
        }
      })

    // 定义匹配模式
    val loginFileEventPattern = Pattern
      .begin[LoginEvent]("begin")
      .where(_.eventType == "fail")
      .next("next")
      .where(_.eventType == "fail")
      .within(Time.seconds(2))
    val patternStream = CEP.pattern(dataSource.keyBy(_.userId), loginFileEventPattern)

    val loginFailEventStream = patternStream
      .select((pattern) => {
        val firstFail = pattern.getOrElse("begin", null).iterator.next()
        val next = pattern.getOrElse("next", null).iterator.next()
        (next.userId, next.ip, next.eventType)
      })
    loginFailEventStream.print()
    env.execute("Login fail detect job")
  }

}
