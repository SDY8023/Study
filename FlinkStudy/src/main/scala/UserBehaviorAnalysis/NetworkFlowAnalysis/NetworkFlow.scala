package UserBehaviorAnalysis.NetworkFlowAnalysis

import UserBehaviorAnalysis.HotItemsAnalysis.bean.{ItemViewCount, UserBehavior}
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.api.java.tuple.{Tuple, Tuple1}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, KeyedStream, StreamExecutionEnvironment, WindowedStream, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

import java.sql.Timestamp
import scala.collection.mutable.ListBuffer

/**
 * @ClassName: NetworkFlow
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/7 21:17
 * */
object NetworkFlow {
  def main(args: Array[String]): Unit = {
    // 创建执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    // 设置process_time为event_time
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    // 读取数据源
    val resource: DataStream[UserBehavior] = env.readTextFile("D:\\study\\code\\Study\\FlinkStudy\\src\\main\\resources\\UserBehavior.csv")
      .map(l => {
        val data = l.split(",")
        UserBehavior(data(0).toLong, data(1).toLong, data(2).toInt, data(2), data(2).toLong)
      })
      // 指定数据中的timestamp为eventTime
      .assignAscendingTimestamps(_.timestamp * 1000)

    val value1: KeyedStream[UserBehavior, Tuple] = resource.keyBy("itemId")
    val value2: WindowedStream[UserBehavior, Tuple, TimeWindow] = value1.timeWindow(Time.minutes(10), Time.seconds(5))
    val value3: DataStream[ItemViewCount] = value2.aggregate(new AggregateFunction[UserBehavior, Long, Long] {
      override def createAccumulator(): Long = 0L

      override def add(value: UserBehavior, accumulator: Long): Long = accumulator + 1

      override def getResult(accumulator: Long): Long = accumulator

      override def merge(a: Long, b: Long): Long = a + b
    }, new WindowFunction[Long, ItemViewCount, Tuple, TimeWindow] {
      override def apply(key: Tuple, window: TimeWindow, input: Iterable[Long], out: Collector[ItemViewCount]): Unit = {
        val itemId = key.asInstanceOf[Tuple1[Long]].f0
        val count = input.iterator.next()
        out.collect(ItemViewCount(itemId, window.getEnd, count))
      }
    })

    // 计算TopN
    value3.keyBy("windowEnd")
      .process(new KeyedProcessFunction[Tuple,ItemViewCount,String] {
        private var itemState:ListState[ItemViewCount] = _

        override def open(parameters: Configuration): Unit = {
          super.open(parameters)
          val value: ListStateDescriptor[ItemViewCount] = new ListStateDescriptor[ItemViewCount]("item-state", classOf[ItemViewCount])
          itemState = getRuntimeContext.getListState(value)
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
          val sortedItems: ListBuffer[ItemViewCount] = allItems.sortBy(_.count)(Ordering.Long.reverse).take(3)
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
      }).print()

    env.execute("hot-item")

  }

}
