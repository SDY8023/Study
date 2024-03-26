package basicStudy.GenericityTest.Test1;

import basicStudy.GenericityTest.Genericity.Apple;
import scala.App;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/3/14 22:24
 **/
public class Test1 {
    public static void main(String[] args) {
        Apple<String> a1 = new Apple<>();

        a1.setWeight("500克");
        Apple<Integer> a2 = new Apple<>();
        a2.setWeight(500);

        Apple<Double> a3 = new Apple<>();
        a3.setWeight(500.0);

        System.out.println("a1:"+a1.getWeight()+" a2:"+a2.getWeight()+" a3:"+a3.getWeight());
    }
}
