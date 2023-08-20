package SeniorStudy.threadTest.test2;

/**
 * @ClassName Communication
 * @Description
 * @Author SDY
 * @Date 2023/7/31 22:24
 **/
public class Communication implements Runnable{
    private int data = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                // 唤醒等待的线程
                this.notify();
                if(data <= 100){
                    System.out.println(Thread.currentThread().getName()+":" + data++);
                }else{
                    break;
                }
                try{
                    // 阻塞当前线程，并释放同步锁，必须等待其他线程唤醒
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }


    }
}
