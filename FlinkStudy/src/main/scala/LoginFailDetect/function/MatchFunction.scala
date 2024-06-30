package LoginFailDetect.function

import LoginFailDetect.bean.{LoginEvent, Warring}
import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
import java.util
import scala.collection.mutable.ListBuffer

/**
 * @author 18438
 * @date 2024/6/23 14:38
 * @description
 */
class MatchFunction extends KeyedProcessFunction[Long,LoginEvent,Warring]{
  lazy val loginState: ListState[LoginEvent] = getRuntimeContext.getListState(new ListStateDescriptor[LoginEvent]("saved login", classOf[LoginEvent]))
  override def processElement(login: LoginEvent, context: KeyedProcessFunction[Long, LoginEvent, Warring]#Context, collector: Collector[Warring]): Unit = {
    if(login.eventType == "fail"){
      val iter = loginState.get().iterator()
      if(iter.hasNext){
        val firstFail = iter.next()
        if(login.eventTime < firstFail.eventTime + 2){
          collector.collect(Warring(login.userId,firstFail.eventTime,login.eventTime,"login fail in 2 seconds"))
        }
        // 把最近一次登录失败的数据更新入state中
        val failList: util.ArrayList[LoginEvent] = new util.ArrayList[LoginEvent]()
        loginState.update(failList)
      }else{
        // 若没有失败数据直接添加进来
        loginState.add(login)
      }
    }else{
      loginState.clear()
    }



    if(login.eventType == "fail"){
      loginState.add(login)
    }
    context.timerService().registerEventTimeTimer(login.eventTime * 1000 + 2 * 1000)
  }

//  override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[Long, LoginEvent, LoginEvent]#OnTimerContext, out: Collector[LoginEvent]): Unit = {
//
//    val allLogins: ListBuffer[LoginEvent] = ListBuffer()
//    import scala.collection.JavaConversions._
//    for(login <- loginState.get()){
//      allLogins += login
//    }
//    loginState.clear()
//    if(allLogins.size > 1){
//      out.collect(allLogins.head)
//    }
//
//  }
}
