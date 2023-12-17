package HotItemsAnalysis.bean

import HotItemsAnalysis.ItemViewCount
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector

import java.sql.Timestamp
import scala.collection.mutable.ListBuffer

/**
 * @ClassName: TopNHotItems
 * @Auther: SDY
 * @Description:
 * @Date: 2023/12/17 16:23
 * */
class TopNHotItems(topSize:Int) extends KeyedProcessFunction[Tuple,ItemViewCount,String]{
  private var itemState : ListState[ItemViewCount] = _

  override def open(parameters: Configuration): Unit = {
    super.open(parameters)

    // 命名状态变量的名字和状态变量的类型
    val itemStateDesc: ListStateDescriptor[ItemViewCount] = new ListStateDescriptor[ItemViewCount]("itemState-state", classOf[ItemViewCount])
    // 定义状态变量
    itemState = getRuntimeContext.getListState(itemStateDesc)
  }

  override def processElement(input: ItemViewCount, ctx: KeyedProcessFunction[Tuple, ItemViewCount, String]#Context, out: Collector[String]): Unit = {
    // 每条数据都保存到状态中
    itemState.add(input)
    // 注册windowEnd+1的EventTime Timer,当触发时，说明收齐了属于windowEnd窗口的所有商品数据
    // 也就是当程序看到windowEnd+1 的水位线watermark 时,触发onTimer回调函数
    ctx.timerService.registerEventTimeTimer(input.windowEnd+1)

  }

  override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[Tuple, ItemViewCount, String]#OnTimerContext, out: Collector[String]): Unit = {
    // 获取收到的所有商品点击量
    val allItems: ListBuffer[ItemViewCount] = ListBuffer()
    import scala.collection.JavaConversions._
    for(item <- itemState.get()){
      allItems += item
    }
    // 提前清除状态中的数据,释放空间
    itemState.clear()
    // 按照点击量从大到小排序
    val sortedItems: ListBuffer[ItemViewCount] = allItems.sortBy(_.count)(Ordering.Long.reverse).take(topSize)
    // 将排名信息格式化成String
    val result: StringBuilder = new StringBuilder()
    result.append("==========================================================\n")
    result.append("时间: ").append(new Timestamp(timestamp - 1)).append("\n")
    for(i <- sortedItems.indices){
      val currentItem: ItemViewCount = sortedItems(i)

      result.append("No").append(i+1).append(":")
        .append(" 商品ID=").append(currentItem.itemId)
        .append(" 浏览量=").append(currentItem.count).append("\n")
    }

    result.append("==========================================================\n\n")

    Thread.sleep(1000)
    out.collect(result.toString())

  }
}




















