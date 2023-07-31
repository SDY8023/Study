package SeniorStudy.threadTest;

/**
 * @ClassName Ticket
 * @Description
 * @Author SDY
 * @Date 2023/7/31 20:59
 **/
public class Ticket implements Runnable{
    private int tick = 100;
    @Override
    public void run() {
        while (true){
            if(tick > 0){
                System.out.println(Thread.currentThread().getName()+"售出车票,tick号为:"+tick--);
            }else{
                break;
            }
        }

    }
}
