package SeniorStudy.threadTest.test4;

/**
 * @ClassName WithDrawThreadTest
 * @Description
 * @Author SDY
 * @Date 2023/8/6 20:46
 **/
public class WithDrawThreadTest {
    public static void main(String[] args) {
        final Account account = new Account("1234567",  10000);
        WithDrawThread xm = new WithDrawThread("小明", account, 8000);
        WithDrawThread xmf = new WithDrawThread("小明's wife", account, 2000);
        xm.start();
        xmf.start();

    }
}
