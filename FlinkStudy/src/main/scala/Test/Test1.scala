package Test

import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * @ClassName: Test1
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/7 22:29
 * */
object Test1 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(3)
    val dataStream: DataStream[(String, String)] = env.fromCollection(List(("1", "a"), ("2", "a"), ("3", "a"), ("4", "a"), ("5", "a")))
    dataStream.print("aaa")
    print("===================")
    dataStream.keyBy(_._1).print("bb")

    env.execute()
  }

}
