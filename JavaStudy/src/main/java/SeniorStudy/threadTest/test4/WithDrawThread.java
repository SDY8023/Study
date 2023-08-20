package SeniorStudy.threadTest.test4;

/**
 * @ClassName Customer
 * @Description
 * @Author SDY
 * @Date 2023/8/6 20:32
 **/
public class WithDrawThread extends Thread{
    Account account;
    double withDraw;

    public WithDrawThread(String name,Account account, double withDraw) {
        super(name);
        this.account = account;
        this.withDraw = withDraw;
    }

    @Override
    public void run() {
        synchronized (account){
            if(account.getBalance() >= withDraw){
                System.out.println(Thread.currentThread().getName()+"：取款成功，取现的金额为:" + withDraw);
                try{
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - withDraw);
            }else{
                System.out.println("取现额度超过账户余额,取款失败");
            }
            System.out.println("现在账户的余额为:"+account.getBalance());
        }
    }
}
