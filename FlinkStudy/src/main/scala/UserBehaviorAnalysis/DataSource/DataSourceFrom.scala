package UserBehaviorAnalysis.DataSource

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

import java.util.Properties

/**
 * @ClassName: DataSource
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/10 21:35
 * */
object DataSourceFrom {
  /**
   * 数据源来自kafka
   *
   * @param env
   */
  def dataFromKafka(): FlinkKafkaConsumer[String] = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "bigdata01:9092,bigdata02:9092,bigdata03:9092")
    properties.setProperty("group.id", "flink-consumer")
    properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("auto.offset.reset", "latest")
    // 读取数据
    new FlinkKafkaConsumer[String]("test2", new SimpleStringSchema(), properties)
  }

}
