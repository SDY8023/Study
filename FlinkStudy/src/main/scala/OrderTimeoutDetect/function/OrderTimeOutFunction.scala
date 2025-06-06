package OrderTimeoutDetect.function

import OrderTimeoutDetect.bean.{OrderEvent, OrderResult}
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector

/**
 * @author 18438
 * @date 2024/6/30 14:55
 * @description
 */
class OrderTimeOutFunction extends KeyedProcessFunction[Long,OrderEvent,OrderResult]{
  lazy val orderState: ListState[OrderEvent] = getRuntimeContext.getListState(new ListStateDescriptor[OrderEvent]("saved order", classOf[OrderEvent]))
  override def processElement(order: OrderEvent, context: KeyedProcessFunction[Long, OrderEvent, OrderResult]#Context, collector: Collector[OrderResult]): Unit = {
    var resultType = "";
    if(order.eventType == "pay"){
      val iter = orderState.get().iterator()
      if(iter.hasNext){
        val firstEvent = iter.next()
        if(firstEvent.eventType == "create"){
          if((order.eventTime - firstEvent.eventTime)/1000/60 > 15){
            resultType = "timeOut"
          }else{
            resultType = "sucess"
          }
        }else{
          resultType = "program error"
        }
      }else{
        resultType = "program error"
      }
      orderState.clear()
    }else{
      resultType = s"${order.orderId} 被创建"
      orderState.add(order)
    }
    collector.collect(OrderResult(orderId = order.orderId, eventType = resultType))
  }
}
