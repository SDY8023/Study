package basicStudy.AbstractTest;

public class B extends A{
    @Override
    void m1() {
        System.out.println("B重写A的m1方法");
    }

    public static void main(String[] args) {
        A b = new B();
        b.m1();
        b.m2();
    }
}
