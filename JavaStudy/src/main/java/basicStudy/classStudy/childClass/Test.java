package basicStudy.classStudy.childClass;

import basicStudy.arrayStudy.test1;

/**
 * @ClassName Test
 * @Description
 * @Author SDY
 * @Date 2023/7/9 21:42
 **/
public class Test {
    public static void main(String[] args) {
        Sub s = new Sub();
        System.out.println(s.count);
        s.display();
        Base b = s;
        System.out.println(b == s);
        System.out.println(b.count);
        b.display();
        String a = "";
    }
}
