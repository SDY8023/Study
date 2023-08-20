package SeniorStudy.threadTest;

/**
 * @ClassName TicketDemo
 * @Description
 * @Author SDY
 * @Date 2023/7/31 21:01
 **/
public class TicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread t1 = new Thread(ticket);
        Thread t2 = new Thread(ticket);
        Thread t3 = new Thread(ticket);
        t1.setName("t1窗口");
        t2.setName("t2窗口");
        t3.setName("t3窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
