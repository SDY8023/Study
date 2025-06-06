package OrderMatch.bean

/**
 * @author 18438
 * @date 2024/6/30 15:30
 * @description
 */
case class OrderEvent(orderId: Long, eventType: String, txId: String, eventTime: Long)
