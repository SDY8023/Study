package basicStudy.InerClass;

/**
 * @ClassName Outer
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:07
 **/
public class Outer {
    private int s;
    public class Inner {
        public void mb(){
            s = 100;
            System.out.println("在内部类Inner中s="+s);
        }
    }
    public void ma(){
        Inner i = new Inner();
        i.mb();
    }
}
