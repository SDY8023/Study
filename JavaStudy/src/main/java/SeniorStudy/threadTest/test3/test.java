package SeniorStudy.threadTest.test3;

/**
 * @ClassName test
 * @Description
 * @Author SDY
 * @Date 2023/8/1 22:04
 **/
public class test {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Thread productThread = new Thread(new Productor(clerk));
        Thread consumerThread = new Thread(new Consumer(clerk));
        productThread.start();
        consumerThread.start();
    }
}
