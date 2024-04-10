package UserBehaviorAnalysis.HotItemsAnalysis.function

import UserBehaviorAnalysis.HotItemsAnalysis.bean.{UserBehavior, UvCount}
import org.apache.flink.streaming.api.scala.function.AllWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
 * @ClassName: UvCountByWindow
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/10 21:55
 * */
class UvCountByWindow extends AllWindowFunction[UserBehavior,UvCount,TimeWindow]{
  override def apply(window: TimeWindow, input: Iterable[UserBehavior], out: Collector[UvCount]): Unit = {
    var idSet = Set[Long]()

    for(userBehavior <- input){
      idSet += userBehavior.userId
    }

    out.collect(UvCount(window.getEnd,idSet.size))

  }
}
