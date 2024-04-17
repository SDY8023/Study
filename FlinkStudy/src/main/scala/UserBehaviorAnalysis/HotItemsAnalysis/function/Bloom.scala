package UserBehaviorAnalysis.HotItemsAnalysis.function

/**
 * @ClassName: Bloom
 * @Auther: SDY
 * @Description:
 * @Date: 2024/4/17 22:04
 * */
class Bloom(size:Long) extends Serializable {
  private val cap = size

  def hash(value:String,seed:Int): Long ={
    var result = 0
    for(i <- 0 until value.length){
      result = result * seed + value.charAt(i)
    }
    (cap - 1) & result
  }

}
