package SeniorStudy.timeTest;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author 18438
 * @date 2023/8/19 17:34
 * @description
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.durationTest();

    }

    /**
     * 时间间隔测试
     */
    public void durationTest(){
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(15, 23, 32);
        System.out.println(localTime);
        System.out.println(localTime1);
        Duration duration = Duration.between(localTime, localTime1);
        System.out.println(duration);
        System.out.println(duration.getSeconds());
        System.out.println(duration.getNano());

    }
}
