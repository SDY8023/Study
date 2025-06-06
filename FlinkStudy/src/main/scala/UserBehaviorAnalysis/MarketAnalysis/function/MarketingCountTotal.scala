package UserBehaviorAnalysis.MarketAnalysis.function

import UserBehaviorAnalysis.MarketAnalysis.bean.MarketingCountView
import units.DateFormatUnits
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
 * @ClassName: MarketingCountTotal
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:27
 * */
class MarketingCountTotal extends ProcessWindowFunction[(String,Long),MarketingCountView,String,TimeWindow]{
  override def process(key: String, context: Context, elements: Iterable[(String, Long)], out: Collector[MarketingCountView]): Unit = {
    // 获取窗口开始结束时间
    val startTs = context.window.getStart
    val endTs = context.window.getEnd
    // 该窗口元素大小
    val count = elements.size
    out.collect(MarketingCountView(DateFormatUnits.formatTs(startTs),
      DateFormatUnits.formatTs(endTs),
      "total",
      "total",
      count
    ))

  }
}
