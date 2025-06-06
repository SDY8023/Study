package PageAdverAnalysis.function

import PageAdverAnalysis.bean.{AdClickLog, BlackListWarning}
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala.OutputTag
import org.apache.flink.util.Collector

/**
 * @ClassName: FilterBlackListUser
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 22:11
 * */
class FilterBlackListUser(maxCount:Long) extends KeyedProcessFunction[(Long,Long),AdClickLog,AdClickLog]{
  val blackListOutputTag = new OutputTag[BlackListWarning]("blacklist")
  // 保存当前用户对当前广告的点击量
  lazy val countState: ValueState[Long] = getRuntimeContext.getState(new ValueStateDescriptor[Long]("count-state", classOf[Long]))
  // 标记前(用户,广告)作为key,是否第一次发送到黑名单
  lazy val firstSent: ValueState[Boolean] = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("firstsent-state", classOf[Boolean]))
  // 保存定时器触发的时间戳，届时清空重置状态
  lazy val resetTime: ValueState[Long] = getRuntimeContext.getState(new ValueStateDescriptor[Long]("resettine-state", classOf[Long]))
  override def processElement(value: AdClickLog, ctx: KeyedProcessFunction[(Long, Long), AdClickLog, AdClickLog]#Context, out: Collector[AdClickLog]): Unit = {
    val curCount: Long = countState.value()
    // 若是第一次处理，注册一个定时器，每天00:00触发清除
    if(curCount == 0){
      val ts = (ctx.timerService().currentProcessingTime() / (24*60*60) + 1) * (24 * 60 * 60 * 1000)
      resetTime.update(ts)
      ctx.timerService().registerProcessingTimeTimer(ts)
    }
    // 若计数已经超上限，则加入黑名单，用侧输出流输出报警信息
    if(curCount > maxCount){
      if(!firstSent.value()){
        firstSent.update(true)
        ctx.output(blackListOutputTag,BlackListWarning(value.userId,value.adId,"Click over"+ maxCount + " times today"))
      }
      return
    }
    // 点击数加1
    countState.update( curCount + 1)
    out.collect(value)
  }

  override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[(Long, Long), AdClickLog, AdClickLog]#OnTimerContext, out: Collector[AdClickLog]): Unit = {
    if(timestamp == resetTime.value()){
      firstSent.clear()
      countState.clear()
    }

  }

}
