package PageAdverAnalysis.bean

/**
 * @ClassNmae: AdClickLog
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:43
 * */
case class AdClickLog(userId:Long,adId:Long,province:String,city:String,timeStamp:Long)

case class CountByProvince(windowEnd:String,province:String,count:Long)

case class BlackListWarning(userId:Long,adId:Long,msg:String)
