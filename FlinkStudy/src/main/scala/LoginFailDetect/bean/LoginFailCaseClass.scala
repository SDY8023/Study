package LoginFailDetect.bean

/**
 * @ClassNmae: LoginFailCaseClass
 * @Auther: SDY
 * @Description:
 * @Date: 2024/5/19 18:22
 * */
case class LoginFailCaseClass()

case class LoginEvent(userId:Long,ip:String,eventType:String,eventTime:Long)
