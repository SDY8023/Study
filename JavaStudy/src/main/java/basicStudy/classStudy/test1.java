package basicStudy.classStudy;

/**
 * @ClassName test1
 * @Description
 * @Author SDY
 * @Date 2023/7/9 9:45
 **/
public class test1 {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;

        new test1().method(a,b);
        System.out.println("a="+a);
        System.out.println("b="+b);

    }
    public void method(int a,int b){
        a = a * 10;
        b = b * 20;
        System.out.println("a="+a);
        System.out.println("b="+b);
        System.exit(0);
    }
}
