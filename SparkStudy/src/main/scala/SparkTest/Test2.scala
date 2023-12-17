package SparkTest

import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.JSON

import java.io.{BufferedReader, File, FileReader, PrintWriter}
import scala.io.Source

/**
 * @ClassName: Test2
 * @Auther: SDY
 * @Description:
 * @Date: 2023/10/22 15:48
 * */
object Test2 {
  def main(args: Array[String]): Unit = {
    // 输入文件和输出文件的路径
    val inputFilePath = "F:\\Study\\spark\\spark3.0优化\\资料\\1.数据文件\\coursepay.log"
    val outputFilePath = "F:\\Study\\spark\\spark3.0优化\\资料\\1.数据文件\\coursepay2.log"

    // 尝试读取输入文件
    val inputFile = Source.fromFile(inputFilePath)
    try {
      // 读取输入文件的内容
      val fileContent = inputFile.mkString
      val value: JSONObject = JSON.parseObject(fileContent)
      print(value.getString("createtime"))


      // 创建或覆盖输出文件并将内容写入
//      val outputFile = new PrintWriter(new File(outputFilePath))
//      try {
//        outputFile.write(fileContent)
//        println("文件内容已成功写入到输出文件。")
//      } finally {
//        outputFile.close()
//      }
    } finally {
      inputFile.close()
    }
  }

}
