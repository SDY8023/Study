package LoginFailDetect.bean

/**
 * @author 18438
 * @date 2024/6/23 14:28
 * @description
 */
case class LoginEvent(userId:Long,ip:String,eventType:String,eventTime:Long)

case class Warring(userId:Long,firstEventTime:Long,valueEventTime:Long,msg:String)
