package basicStudy.FctoryModel.Factory2;

import basicStudy.FctoryModel.NoFactory.Car;

/**
 * @ClassName Client
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:45
 **/
public class Client {
    public static void main(String[] args) {
        Car audi = new AudiFactory().getCar();
        Car byd = new BYDFactory().getCar();
        audi.run();
        byd.run();
    }
}
