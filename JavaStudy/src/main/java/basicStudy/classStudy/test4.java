package basicStudy.classStudy;

import basicStudy.classStudy.childClass.Person;

/**
 * @ClassName test4
 * @Description
 * @Author SDY
 * @Date 2023/7/10 22:18
 **/
public class test4 {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        System.out.println(p1.getId());
        System.out.println(p2.getId());
        System.out.println(p3.getId());
    }
}
