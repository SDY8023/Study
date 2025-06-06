package Other;

/**
 * @author 18438
 * @date 2023/10/29 23:10
 * @description 装箱与拆箱的区别
 */
public class PackingAndUnpacking {
    public static void main(String[] args) {
        test1();

    }

    /**
     * 装箱的区别
     */
    public static void test1(){
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 129;
        Integer i4 = 129;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);

    }
}
