package UserBehaviorAnalysis.HotItemsAnalysis.function

import UserBehaviorAnalysis.HotItemsAnalysis.bean.ItemViewCount
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.configuration
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector

import java.sql.Timestamp
import scala.collection.mutable.ListBuffer

/**
 * @author 18438
 * @date 2024/4/6 16:24
 * @description
 */
class TopNHotItems(topSize:Int) extends KeyedProcessFunction[Tuple,ItemViewCount,String]{
  private var itemState:ListState[ItemViewCount] = _

  override def open(parameters: configuration.Configuration): Unit = {
    super.open(parameters)
    val itemsStateDesc: ListStateDescriptor[ItemViewCount] = new ListStateDescriptor[ItemViewCount]("itemState-state", classOf[ItemViewCount])
    itemState = getRuntimeContext.getListState(itemsStateDesc)
  }
  override def processElement(i: ItemViewCount, context: KeyedProcessFunction[Tuple, ItemViewCount, String]#Context, collector: Collector[String]): Unit = {
    itemState.add(i)
    // 当程序看到windowEnd +1的waterMark时，触发onTimer回调函数
    context.timerService().registerEventTimeTimer(i.windowEnd + 1)
  }

  override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[Tuple, ItemViewCount, String]#OnTimerContext, out: Collector[String]): Unit = {
    val allItems: ListBuffer[ItemViewCount] = ListBuffer()
    import scala.collection.JavaConversions._
    for(item <- itemState.get){
      allItems += item
    }
    // 提前清楚状态中的数据，释放空间
    itemState.clear()
    // 按照点击量排序
    val sortedItems: ListBuffer[ItemViewCount] = allItems.sortBy(_.count)(Ordering.Long.reverse).take(topSize)
    // 格式化信息
    val result = new StringBuilder()
    result.append("==================================\n")
    result.append("时间: ").append(new Timestamp(timestamp-1)).append("\n")
    for(i <- sortedItems.indices){
      val currentItem: ItemViewCount = sortedItems(i)
      result.append("No").append(i+1).append(":")
        .append(" 商品ID=").append(currentItem.itemId)
        .append(" 浏览量=").append(currentItem.count).append("\n")
    }
    result.append("==================================\n\n")
    Thread.sleep(1000)
    out.collect(result.toString())
  }
}
