package basicStudy.FctoryModel.SampleFactory;

import basicStudy.FctoryModel.NoFactory.Car;

/**
 * @ClassName Client2
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:40
 **/
public class Client2 {
    public static void main(String[] args) {
        Car audi = CarFactory.getCar("Audi");
        Car byd = CarFactory.getCar("BYD");
        audi.run();
        byd.run();
    }
}
