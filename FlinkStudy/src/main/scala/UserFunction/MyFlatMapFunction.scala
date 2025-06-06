package UserFunction

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.util.Collector

/**
 * @ClassName: MyFlatMapFunction
 * @Auther: SDY
 * @Description:
 * @Date: 2024/12/19 21:20
 * */
class MyFlatMapFunction extends FlatMapFunction[String,String] with Serializable {
  override def flatMap(value: String, out: Collector[String]): Unit = {
    val strings = value.split(",")
    strings.foreach(x => out.collect(x))
  }
}
