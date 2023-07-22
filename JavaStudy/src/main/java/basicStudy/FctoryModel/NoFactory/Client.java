package basicStudy.FctoryModel.NoFactory;

/**
 * @ClassName Client
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:35
 **/
public class Client {
    public static void main(String[] args) {
        Audi audi = new Audi();
        BYD byd = new BYD();
        audi.run();
        byd.run();
    }
}
