package Test

import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

import java.util.UUID

/**
 * @ClassName: Test1
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/7 22:29
 * */
object Test1 {
  def main(args: Array[String]): Unit = {
    val id = UUID.randomUUID().toString
    print(id)
  }

}
