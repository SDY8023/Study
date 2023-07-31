package SeniorStudy.threadTest.test;

/**
 * @ClassName Customer
 * @Description
 * @Author SDY
 * @Date 2023/7/31 21:52
 **/
public class Customer extends Thread{
    private BankCount acct;

    public Customer(BankCount acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            acct.deposit(1000);
        }
    }
}
