package SeniorStudy.threadTest;

/**
 * @ClassName MyThread2
 * @Description
 * @Author SDY
 * @Date 2023/7/30 20:40
 **/
public class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            if(i % 2 != 0){
                System.out.println(i);
            }

        }
    }
}
