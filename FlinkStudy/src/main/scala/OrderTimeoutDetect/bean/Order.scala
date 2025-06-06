package OrderTimeoutDetect.bean

/**
 * @author 18438
 * @date 2024/6/30 14:17
 * @description
 */
case class Order()

case class OrderEvent(orderId:Long,eventType:String,eventTime:Long)

case class OrderResult(orderId:Long,eventType:String)
