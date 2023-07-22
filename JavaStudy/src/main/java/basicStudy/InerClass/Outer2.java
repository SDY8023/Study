package basicStudy.InerClass;

/**
 * @ClassName Outer2
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:13
 **/
public class Outer2 {
    private int s = 111;
    public class Inner{
        private int s = 222;
        public void mb(int s){
            System.out.println(s); // 局部变量
            System.out.println(this.s); // 内部类对象的属性s
            System.out.println(Outer2.this.s); // 外部类对象属性s
        }
    }

    public static void main(String[] args) {
        Outer2 o = new Outer2();
        Outer2.Inner b = o.new Inner();
        b.mb(333);
    }
}
