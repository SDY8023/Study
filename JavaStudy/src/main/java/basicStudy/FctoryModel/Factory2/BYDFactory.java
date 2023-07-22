package basicStudy.FctoryModel.Factory2;

import basicStudy.FctoryModel.NoFactory.BYD;
import basicStudy.FctoryModel.NoFactory.Car;

/**
 * @ClassName BYDFactory
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:45
 **/
public class BYDFactory implements Factory{
    @Override
    public Car getCar() {
        return new BYD();
    }
}
