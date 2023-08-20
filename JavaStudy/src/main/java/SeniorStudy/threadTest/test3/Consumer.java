package SeniorStudy.threadTest.test3;

/**
 * @ClassName Consumer
 * @Description
 * @Author SDY
 * @Date 2023/8/1 22:03
 **/
public class Consumer implements Runnable{
    Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者我开始取走产品");
        while (true){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            clerk.getProduct();
        }
    }
}
