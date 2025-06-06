package UserBehaviorAnalysis.HotItemsAnalysis.function

import UserBehaviorAnalysis.HotItemsAnalysis.bean.UvCount
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import redis.clients.jedis.Jedis

/**
 * @ClassName: UvCountWithBloom
 * @Auther: SDY
 * @Description: 自定义窗口处理函数
 * @Date: 2024/4/17 21:41
 * */
class UvCountWithBloom extends ProcessWindowFunction[(String,Long),UvCount,String,TimeWindow]{
  lazy val jedis = new Jedis("localhost", 6379)
  lazy val bloom = new Bloom(1 << 29) // lazy 修饰的对象，初始化会被延迟到第一次使用该常量的时候
  override def process(key: String, context: Context, elements: Iterable[(String, Long)], out: Collector[UvCount]): Unit = {
    val storeKey = context.window.getEnd.toString
    var count = 0L
    if(jedis.hget("count",storeKey) != null){
      count = jedis.hget("count",storeKey).toLong
    }

    val userId = elements.last._2.toString
    val offset = bloom.hash(userId, 61)

    val isExist = jedis.getbit(storeKey, offset)
    if(!isExist){
      jedis.setbit(storeKey,offset,true)
      jedis.hset("count",storeKey,(count + 1).toString)
      out.collect(UvCount(storeKey.toLong,count + 1))
    }else{
      out.collect(UvCount(storeKey.toLong,count))
    }


  }
}
