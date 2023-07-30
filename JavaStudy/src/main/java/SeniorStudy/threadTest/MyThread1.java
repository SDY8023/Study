package SeniorStudy.threadTest;

/**
 * @ClassName test1
 * @Description
 * @Author SDY
 * @Date 2023/7/30 20:36
 **/
public class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            if(i % 2 == 0){
                System.out.println(i);
            }

        }
    }
}
