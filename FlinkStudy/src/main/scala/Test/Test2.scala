package Test

import UserFunction.MyFlatMapFunction
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

import java.util.Properties

/**
 * @ClassName: Test2
 * @Auther: SDY
 * @Description:
 * @Date: 2024/12/17 10:51
 * */
object Test2 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val properties = new Properties()
    properties.setProperty("bootstrap.servers","192.168.10.101:9092,192.168.10.102:9092,192.168.10.103:9092")
    properties.setProperty("group.id","sdyTest1")
    properties.setProperty("auto.offset.reset","latest")

    val consumer: FlinkKafkaConsumer[String] = new FlinkKafkaConsumer[String]("test1", new SimpleStringSchema(), properties)
    val dataSource = env.addSource(consumer).setParallelism(2)


    val ds2: DataStream[String] = dataSource.flatMap(new MyFlatMapFunction).setParallelism(3)

    val ds3: DataStream[(String, Int)] = ds2.map(x => (x, 1)).setParallelism(4)

    val value = ds3.keyBy(_._1).sum(1).setParallelism(4)
    value.slotSharingGroup("group-1")
    value.print("result:").setParallelism(3)

    env.execute("test1")

  }

}
