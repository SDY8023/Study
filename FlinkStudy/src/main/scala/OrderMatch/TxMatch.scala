package OrderMatch

import OrderMatch.bean.{OrderEvent, ReceiptEvent}
import OrderMatch.function.TxMatchDetection
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.{DataStream, KeyedStream, OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author 18438
 * @date 2024/6/30 15:21
 * @description
 */
object TxMatch {
  val unmatchedPays = new OutputTag[OrderEvent]("unmatchedPays")
  val unmatchedReceipts = new OutputTag[ReceiptEvent]("unmatchedReceipts")

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val orderLogPath = "D:\\File\\yang\\code\\idea\\Study\\FlinkStudy\\src\\main\\resources\\OrderLog.csv"
    val receiptLogPath = "D:\\File\\yang\\code\\idea\\Study\\FlinkStudy\\src\\main\\resources\\ReceiptLog.csv"
    val orderEventStream: KeyedStream[OrderEvent, String] = env.readTextFile(orderLogPath)
      .map(data => {
        val dataArray = data.split(",")
        OrderEvent(dataArray(0).toLong, dataArray(1), dataArray(2), dataArray(3).toLong)
      })
      .filter(_.txId != "")
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[OrderEvent](Time.milliseconds(3000)) {
        override def extractTimestamp(t: OrderEvent): Long = t.eventTime * 1000
      })
      .keyBy(_.txId)

    val receiptEventStream: KeyedStream[ReceiptEvent, String] = env.readTextFile(receiptLogPath)
      .map(data => {
        val dataArray = data.split(",")
        ReceiptEvent(dataArray(0), dataArray(1), dataArray(2).toLong)
      })
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[ReceiptEvent](Time.milliseconds(3000)) {
        override def extractTimestamp(t: ReceiptEvent): Long = t.eventTime * 1000L
      })
      .keyBy(_.txId)

    val processedStream = orderEventStream
      .connect(receiptEventStream)
      .process(new TxMatchDetection)

    processedStream.getSideOutput(unmatchedPays)
      .print("unmatched pays")

    processedStream.getSideOutput(unmatchedReceipts)
      .print("unmatched receipts")

    processedStream.print()

    env.execute("processed")

  }


}
