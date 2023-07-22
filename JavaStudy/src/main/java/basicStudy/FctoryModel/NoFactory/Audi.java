package basicStudy.FctoryModel.NoFactory;

/**
 * @ClassName Audi
 * @Description
 * @Author SDY
 * @Date 2023/7/22 21:34
 **/
public class Audi implements Car{
    @Override
    public void run() {
        System.out.println("奥迪");
    }
}
