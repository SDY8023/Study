package OrderMatch.function

import OrderMatch.TxMatch.{unmatchedPays, unmatchedReceipts}
import OrderMatch.bean.{OrderEvent, ReceiptEvent}
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.functions.co.CoProcessFunction
import org.apache.flink.util.Collector

/**
 * @author 18438
 * @date 2024/6/30 15:39
 * @description
 */
class TxMatchDetection extends CoProcessFunction[OrderEvent,ReceiptEvent,(OrderEvent,ReceiptEvent)]{
  lazy val payState: ValueState[OrderEvent] = getRuntimeContext.getState(new ValueStateDescriptor[OrderEvent]("pay-state", classOf[OrderEvent]))
  lazy val receiptEventState: ValueState[ReceiptEvent] = getRuntimeContext.getState(new ValueStateDescriptor[ReceiptEvent]("receipt-state", classOf[ReceiptEvent]))

  override def processElement1(pay: OrderEvent, context: CoProcessFunction[OrderEvent, ReceiptEvent, (OrderEvent, ReceiptEvent)]#Context, collector: Collector[(OrderEvent, ReceiptEvent)]): Unit = {
    val receipt: ReceiptEvent = receiptEventState.value()
    if(receipt != null){
      receiptEventState.clear()
      collector.collect((pay,receipt))
    }else{
      payState.update(pay)
      context.timerService().registerEventTimeTimer(pay.eventTime * 1000L)
    }


  }

  override def processElement2(receipt: ReceiptEvent, context: CoProcessFunction[OrderEvent, ReceiptEvent, (OrderEvent, ReceiptEvent)]#Context, collector: Collector[(OrderEvent, ReceiptEvent)]): Unit = {
    val payment = payState.value()
    if(payment != null){
      collector.collect((payment,receipt))
    }else{
      receiptEventState.update(receipt)
      context.timerService().registerEventTimeTimer(receipt.eventTime * 1000L)
    }

  }

  override def onTimer(timestamp: Long, ctx: CoProcessFunction[OrderEvent, ReceiptEvent, (OrderEvent, ReceiptEvent)]#OnTimerContext, out: Collector[(OrderEvent, ReceiptEvent)]): Unit = {

    if(payState.value() != null){
      ctx.output(unmatchedPays,payState.value())
    }
    if(receiptEventState.value() != null){
      ctx.output(unmatchedReceipts,receiptEventState.value())
    }
    payState.clear()
    receiptEventState.clear()
  }
}
