package SeniorStudy.threadTest.test2;

/**
 * @ClassName test
 * @Description
 * @Author SDY
 * @Date 2023/7/31 22:26
 **/
public class test {
    public static void main(String[] args) {
        Communication communication = new Communication();
        Thread t1 = new Thread(communication);
        Thread t2 = new Thread(communication);
        t1.setName("Thread1");
        t2.setName("Thread2");
        t1.start();
        t2.start();
    }
}
