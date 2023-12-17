package HotItemsAnalysis.bean

import HotItemsAnalysis.UserBehavior
import org.apache.flink.api.common.functions.AggregateFunction

/**
 * @ClassName: CountAgg
 * @Auther: SDY
 * @Description:
 * @Date: 2023/12/17 15:56
 * */
class CountAgg extends AggregateFunction[UserBehavior,Long,Long]{
  override def createAccumulator(): Long = 0L

  override def add(value: UserBehavior, accumulator: Long): Long = accumulator + 1

  override def getResult(accumulator: Long): Long = accumulator

  override def merge(a: Long, b: Long): Long = a + b
}
