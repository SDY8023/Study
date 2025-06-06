package PageAdverAnalysis.function

import PageAdverAnalysis.bean.AdClickLog
import org.apache.flink.api.common.functions.AggregateFunction

/**
 * @ClassName: CountAgg
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:54
 * */
class CountAgg extends AggregateFunction[AdClickLog,Long,Long]{
  override def createAccumulator(): Long = {
    0L
  }

  override def add(value: AdClickLog, accumulator: Long): Long = accumulator + 1L

  override def getResult(accumulator: Long): Long = accumulator

  override def merge(a: Long, b: Long): Long = a + b
}
