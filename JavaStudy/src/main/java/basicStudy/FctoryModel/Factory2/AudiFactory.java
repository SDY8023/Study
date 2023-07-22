package basicStudy.FctoryModel.Factory2;

import basicStudy.FctoryModel.NoFactory.Audi;
import basicStudy.FctoryModel.NoFactory.Car;

import javax.sound.sampled.AudioInputStream;

/**
 * @ClassName AudiFactory
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:44
 **/
public class AudiFactory implements Factory{
    @Override
    public Car getCar() {
        return new Audi();
    }
}
