package PageAdverAnalysis.function

import PageAdverAnalysis.bean.{AdClickLog, CountByProvince}
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import units.DateFormatUnits

/**
 * @ClassName: CountResult
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:56
 * */
class CountResult extends WindowFunction[Long,CountByProvince,String,TimeWindow]{
  override def apply(key: String, window: TimeWindow, input: Iterable[Long], out: Collector[CountByProvince]): Unit = {
    out.collect(CountByProvince(DateFormatUnits.formatTs(window.getEnd),key,input.iterator.next()))

  }
}
