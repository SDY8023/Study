package SeniorStudy.threadTest.test3;

/**
 * @ClassName Clerk
 * @Description
 * @Author SDY
 * @Date 2023/8/1 21:57
 **/
public class Clerk {
    private int product = 0;
    public synchronized void addProduct(){
        if(product >= 20){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else{
            product++;
            System.out.println("生产者生产了 "+product+" 个产品");
            this.notifyAll();
        }
    }
    public synchronized void getProduct(){
        if(this.product <= 0){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("消费者取走了第 "+product+" 个产品");
            product--;
            this.notifyAll();
        }
    }
}
