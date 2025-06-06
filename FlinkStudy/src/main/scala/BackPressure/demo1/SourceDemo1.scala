package BackPressure.demo1

import kafka.utils.Whitelist
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
/**
 * @ClassName: SourceDemo1
 * @Auther: SDY
 * @Description:
 * @Date: 2024/7/9 21:36
 * */
class SourceDemo1 extends RichParallelSourceFunction[String]{
  override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    val dataList = ListBuffer("河南省", "广东省", "江苏省", "北京市", "重庆市")
    val random = new Random()
    val i = random.nextInt(5)
    while(true){
      ctx.collect(dataList(i)+","+random.nextInt(100)+","+random.nextInt(100))
      Thread.sleep(10)
    }

  }

  override def cancel(): Unit = {

  }
}
