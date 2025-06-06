package UserBehaviorAnalysis.MarketAnalysis.source

import UserBehaviorAnalysis.MarketAnalysis.bean.MarketingUserBehavior
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

import java.util.UUID
import java.util.concurrent.TimeUnit
import scala.util.Random

/**
 * @ClassName: SimulateEventSource
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/20 20:11
 * */
class SimulateEventSource(maxElements:Long) extends RichParallelSourceFunction[MarketingUserBehavior]{
  var running = true
  val channelSet:Seq[String] = Seq("AppStore","XiaomiStore","HuaweiStore","weibo","wechat","tieba")
  val behaviorTypes:Seq[String] = Seq("BROWSE","CLICK","PURCHASE","UNINSTALL")
  val rand:Random = Random
  override def run(ctx: SourceFunction.SourceContext[MarketingUserBehavior]): Unit = {
    var count = 0L
    while (running && count < maxElements){
      val id: String = UUID.randomUUID().toString
      val behaviorType = behaviorTypes(rand.nextInt(behaviorTypes.size))
      val channel = channelSet(rand.nextInt(channelSet.size))
      val ts = System.currentTimeMillis()

      ctx.collectWithTimestamp(MarketingUserBehavior(id,behaviorType,channel,ts),ts)
      count += 1
      TimeUnit.MILLISECONDS.sleep(5L)
    }

  }

  override def cancel(): Unit = running = false
}
