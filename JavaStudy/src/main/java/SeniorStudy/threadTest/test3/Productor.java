package SeniorStudy.threadTest.test3;

/**
 * @ClassName Productor
 * @Description
 * @Author SDY
 * @Date 2023/8/1 22:00
 **/
public class Productor implements Runnable{
    Clerk clerk;
    public Productor(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产产品");
        while (true){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}
