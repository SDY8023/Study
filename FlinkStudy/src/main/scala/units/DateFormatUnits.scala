package units

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @ClassName: DateFormatUnits
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/23 21:31
 * */
object DateFormatUnits {
  def formatTs(ts:Long) ={
    val df = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss")
    df.format(new Date(ts))
  }

}
