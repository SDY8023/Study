package Test

import Test.CaseClass.SensorReading
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.{ConnectedStreams, DataStream, SplitStream, StreamExecutionEnvironment}

/**
 * @ClassName: Test1
 * @Auther: SDY
 * @Description:
 * @Date: 2024/3/31 12:42
 * */
object Test1 {
  var env: StreamExecutionEnvironment = null
  def main(args: Array[String]): Unit = {
    env = StreamExecutionEnvironment.getExecutionEnvironment
    connectAndCoMap()
    env.execute()

  }

  def connectAndCoMap(): Unit ={
    val stream1: DataStream[SensorReading] = env.fromCollection(List(
      SensorReading("sensor_1", 1547718199, 35.8),
      SensorReading("sensor_2", 1547718199, 36.8),
      SensorReading("sensor_3", 1547718199, 37.8),
      SensorReading("sensor_4", 1547718199, 35.9),
      SensorReading("sensor_1", 1547718199, 35.9),
      SensorReading("sensor_5", 1547718199, 25.9),
      SensorReading("sensor_6", 1547718199, 26.9)
    ))
    val splitStream1: SplitStream[SensorReading] = stream1.split(s => {
      if (s.temperature > 30) Seq("high") else Seq("low")
    })
    val highStream: DataStream[SensorReading] = splitStream1.select("high")
    val lowStream: DataStream[SensorReading] = splitStream1.select("low")
    highStream.print("high")
    lowStream.print("low")
    val waring = highStream.map(x => (x.id, x.temperature))
    val connectStream: ConnectedStreams[(String, Double), SensorReading] = waring.connect(lowStream)
    val coMap: DataStream[Product with Serializable] = connectStream.map(x => (x._1, x._2, "warning"), y => (y.id, "healthy"))
    coMap.print()
  }

}
