package BackPressure.demo1

import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * @ClassName: Demo1
 * @Auther: SDY
 * @Description:
 * @Date: 2024/7/9 21:34
 * */
object Demo1 {
  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(2)
    // 为方便测试断开算子间的连接
    env.disableOperatorChaining()

    val dataSource = env.addSource(new SourceDemo1)

    val mapData: DataStream[(String, String, String)] = dataSource.map(x => {
      val strings = x.split(",")
      Thread.sleep(10000)
      (strings(0), strings(1), strings(2))
    })

    mapData.keyBy(_._1)
      .reduce((x1,x2) => (x1._1,x1._2,(x1._3.toInt + x2._3.toInt).toString))
      .print()

    env.execute("test")
  }

}
