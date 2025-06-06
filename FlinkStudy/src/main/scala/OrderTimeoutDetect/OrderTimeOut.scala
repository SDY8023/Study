package OrderTimeoutDetect

import OrderTimeoutDetect.bean.{OrderEvent, OrderResult}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.cep.scala.{CEP, PatternStream}
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author 18438
 * @date 2024/6/30 14:17
 * @description
 */
object OrderTimeOut {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val dataStream: DataStream[OrderEvent] = env.readTextFile("D:\\File\\yang\\code\\idea\\Study\\FlinkStudy\\src\\main\\resources\\OrderLog.csv")
      .map(data => {
        val dataArray = data.split(",")
        OrderEvent(dataArray(0).toLong, dataArray(1), dataArray(3).toLong)
      })
      .assignAscendingTimestamps(_.eventTime * 1000)

    // 定义一个带匹配时间窗口的模式
    val orderPattern: Pattern[OrderEvent, OrderEvent] = Pattern.begin[OrderEvent]("begin")
      .where(_.eventType == "create")
      .followedBy("follow")
      .where(_.eventType == "pay")
      .within(Time.minutes(15))

    // 定义一个输出标签
    val orderTimeOutput = OutputTag[OrderResult]("orderTimeOut")
    // 订单时间根据orderId分流 然后在每一条流中匹配出定义好的模式
    val patternStream: PatternStream[OrderEvent] = CEP.pattern(dataStream.keyBy(_.orderId), orderPattern)

    val completedResult = patternStream.select(orderTimeOutput){
      // 对于已超时的部分模式匹配事件序列，会调用这个函数
      (pattern,timestamp:Long) => {
        val createOrder = pattern.get("begin")
        OrderResult(createOrder.get.iterator.next().orderId,"timeOut")
      }
    }{
      // 检测到定义好的模式序列时，就会调用这个函数
      pattern => {
        val payOrder: Option[Iterable[OrderEvent]] = pattern.get("follow")
        OrderResult(payOrder.get.iterator.next().orderId,"sucess")
      }
    }

    // 拿到同一输出标签中的timeOut 匹配结果流
    val timeoutResult: DataStream[OrderResult] = completedResult.getSideOutput(orderTimeOutput)

    completedResult.print()
    timeoutResult.print()

    env.execute("Order Timeout Detect Job")

  }
}
