package SeniorStudy.threadTest.test;

/**
 * @ClassName test1
 * @Description
 * @Author SDY
 * @Date 2023/7/31 21:34
 **/
public class test1 {
    public static void main(String[] args) {
        BankCount bankCount = new BankCount(0);
        Customer c1 = new Customer(bankCount);
        Customer c2 = new Customer(bankCount);
        c1.setName("储户1");
        c2.setName("储户2");
        c1.start();
        c2.start();

    }
}
