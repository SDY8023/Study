package SeniorStudy.threadTest.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BankCount
 * @Description
 * @Author SDY
 * @Date 2023/7/31 21:26
 **/
public class BankCount{
    private int balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankCount(int balance) {
        this.balance = balance;
    }

    public void deposit(int money){
        lock.lock();
        try{
            if(money > 0){
                balance += money;
                System.out.println(Thread.currentThread().getName()+"存入"+money+",余额:"+balance);
            }
        }finally {
            lock.unlock();
        }
    }
}
