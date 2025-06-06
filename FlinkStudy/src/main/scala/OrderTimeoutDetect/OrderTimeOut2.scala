package OrderTimeoutDetect

import OrderTimeoutDetect.bean.OrderEvent
import OrderTimeoutDetect.function.OrderTimeOutFunction
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author 18438
 * @date 2024/6/30 14:49
 * @description
 */
object OrderTimeOut2 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val filePath = "D:\\File\\yang\\code\\idea\\Study\\FlinkStudy\\src\\main\\resources\\OrderLog.csv"
    val dataSourceStream: DataStream[OrderEvent] = env.readTextFile(filePath)
      .map(data => {
        val dataArray = data.split(",")
        OrderEvent(dataArray(0).toLong, dataArray(1), dataArray(3).toLong)
      })
      // 定义水位线，每三秒做一次，并设置时间的eventTime为时间流
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[OrderEvent](Time.milliseconds(3000)) {
        override def extractTimestamp(t: OrderEvent): Long = {
          t.eventTime * 1000L
        }
      })

    dataSourceStream.keyBy(_.orderId)
      .process(new OrderTimeOutFunction())
      .print()

    env.execute("Order Timeout Detect Job")

  }

}
