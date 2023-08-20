package SeniorStudy.threadTest;

/**
 * @ClassName MyThreadTest
 * @Description
 * @Author SDY
 * @Date 2023/7/30 20:41
 **/
public class MyThreadTest {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.start();
        myThread2.start();
    }
}
