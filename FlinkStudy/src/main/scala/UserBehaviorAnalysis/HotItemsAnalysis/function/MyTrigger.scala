package UserBehaviorAnalysis.HotItemsAnalysis.function

import org.apache.flink.streaming.api.windowing.triggers.{Trigger, TriggerResult}
import org.apache.flink.streaming.api.windowing.windows.TimeWindow

/**
 * 自定义窗口的触发器
 * 1。需要实现trigger
 * 2. 第一个泛型是输入数据类型，第二个泛型是window的类型
 * 3. 实现4个方法
 * @ClassName: MyTrigger
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/17 20:58
 * */
class MyTrigger extends Trigger[(String,Long),TimeWindow]{
  /**
   * 窗口中每添加一个事件数据调用一次，每条数据执行一次
   * @param t
   * @param l
   * @param w
   * @param triggerContext
   * @return
   */
  override def onElement(t: (String, Long), l: Long, w: TimeWindow, triggerContext: Trigger.TriggerContext): TriggerResult = {
    TriggerResult.FIRE_AND_PURGE
  }

  /**
   * 当已注册的处理时间计时器被触发时，调用该方法
   * @param l
   * @param w
   * @param triggerContext
   * @return
   */
  override def onProcessingTime(l: Long, w: TimeWindow, triggerContext: Trigger.TriggerContext): TriggerResult = {
    TriggerResult.CONTINUE
  }

  /**
   * 当已注册的事件时间计时器被触发时，调用该方法。换句话基于事件时间的数据且达到计时器条件。
   * @param l
   * @param w
   * @param triggerContext
   * @return
   */
  override def onEventTime(l: Long, w: TimeWindow, triggerContext: Trigger.TriggerContext): TriggerResult = {
    TriggerResult.CONTINUE
  }

  /**
   * 执行删除相应窗口所需的任何操作。
   * @param w
   * @param triggerContext
   */
  override def clear(w: TimeWindow, triggerContext: Trigger.TriggerContext): Unit = {

  }
}
