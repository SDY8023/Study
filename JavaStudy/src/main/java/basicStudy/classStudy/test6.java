package basicStudy.classStudy;

import basicStudy.AbstractTest.Manager;

/**
 * @ClassName test6
 * @Description
 * @Author SDY
 * @Date 2023/7/19 20:30
 **/
public class test6 {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setId("00001");
        System.out.println(manager.work());
    }
}
