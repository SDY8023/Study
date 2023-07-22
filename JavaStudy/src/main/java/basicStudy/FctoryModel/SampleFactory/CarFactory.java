package basicStudy.FctoryModel.SampleFactory;

import basicStudy.FctoryModel.NoFactory.Audi;
import basicStudy.FctoryModel.NoFactory.BYD;
import basicStudy.FctoryModel.NoFactory.Car;

/**
 * @ClassName CarFactory
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:38
 **/
public class CarFactory {
    public static Car getCar(String type){
        if("Audi".equals(type)){
            return new Audi();
        }else if("BYD".equals(type)){
            return new BYD();
        }else{
            return null;
        }
    }
}
