package Test

/**
 * @author 18438
 * @date 2024/11/10 11:18
 * @description
 */
class TestClass2(val name:String,val country:String) {

}

object TestClass2{
  def apply(name: String, country: String): TestClass2 = {
    new TestClass2(name, country)
  }

  def unapply(data: TestClass2): Option[(String, String)] = {
    Some(data.name, data.country)
  }
}
