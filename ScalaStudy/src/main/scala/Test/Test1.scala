package Test

import scala.collection.BitSet

/**
 * @author 18438
 * @date 2024/11/10 9:46
 * @description
 */
object Test1 {
  def main(args: Array[String]): Unit = {
    testLazy()
  }

  def testBitSet(): Unit = {
    val bitSet:BitSet = BitSet(1,2,3,4)
    println(bitSet(1),bitSet(5))
    print(bitSet)
  }

  def testOption(): Unit = {
    val dataMap = Map("k1" -> "v1", "k2" -> "v2")
    val maybeString: Option[String] = dataMap.get("k1")
    val maybeString2: Option[String] = dataMap.get("k3")
    println(maybeString.get,maybeString2.isEmpty)

  }

  def test3(): Unit = {
    val a = TestClass2("aa", "bb")
    a match {
      case TestClass2("aa", "bb") => println("11")
      case _ => println("no data")
    }

    val t3 = new TestClass3()

  }


  def test4(): Unit = {
    implicit def intToString(a:Int):String = a.toString

    val c:Int = 10

  }

  def testLazy(): Unit = {
    lazy val expensiveValue:Int = {
      println("test lazy.....")
      2
    }

    // 第一次加载
    println(expensiveValue)

    // 第二次加载
    println(expensiveValue)
  }

}
