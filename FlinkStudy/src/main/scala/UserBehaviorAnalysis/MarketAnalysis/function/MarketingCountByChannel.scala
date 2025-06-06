package UserBehaviorAnalysis.MarketAnalysis.function

import UserBehaviorAnalysis.MarketAnalysis.bean.MarketingCountView
import units.DateFormatUnits.formatTs
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @ClassName: MarketingCountByChannel
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/20 20:31
 * */
class MarketingCountByChannel extends ProcessWindowFunction[((String,String),Long),String,(String,String),TimeWindow]{
  override def process(key: (String, String), context: Context, elements: Iterable[((String, String), Long)], out: Collector[String]): Unit = {
    val startTs = context.window.getStart
    val endTs = context.window.getEnd
    val channel = key._1
    val behaviorType = key._2
    val count = elements.size
    //out.collect(MarketingCountView(formatTs(startTs),formatTs(endTs),channel,behaviorType,count))
    val result = new StringBuffer()
    result.append(s"startTs:${formatTs(startTs)} - endTs:${formatTs(endTs)}\n")
      .append(s"channel:${channel},behaviorType:${behaviorType},count:${count}\n")

    out.collect(result.toString)

  }

}
