package LoginFailDetect.function

import LoginFailDetect.bean.LoginEvent
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector

import scala.collection.mutable.ListBuffer

/**
 * @ClassName: MatchFunction
 * @Auther: SDY
 * @Description:
 * @Date: 2024/5/19 18:30
 * */
class MatchFunction extends KeyedProcessFunction[Long,LoginEvent,LoginEvent]{

  // 定义状态变量
  lazy val loginState: ListState[LoginEvent] = getRuntimeContext.getListState(new ListStateDescriptor[LoginEvent]("saved login", classOf[LoginEvent]))
  override def processElement(value: LoginEvent,
                              ctx: KeyedProcessFunction[Long, LoginEvent, LoginEvent]#Context,
                              out: Collector[LoginEvent]): Unit = {
    if(value.eventType.equals("fail")){
      loginState.add(value)
    }

    // 注册定时器，触发事件设定为2秒后
    ctx.timerService().registerEventTimeTimer(value.eventTime * 1000 + 2 * 1000)
  }

  override def onTimer(timestamp: Long,
                       ctx: KeyedProcessFunction[Long, LoginEvent, LoginEvent]#OnTimerContext,
                       out: Collector[LoginEvent]): Unit = {
    val allLogins: ListBuffer[LoginEvent] = ListBuffer()
    import scala.collection.JavaConversions._
    for(login <- loginState.get()){
      allLogins += login
    }
    loginState.clear()
    if(allLogins.length > 1){
      out.collect(allLogins.head)
    }
  }
}
