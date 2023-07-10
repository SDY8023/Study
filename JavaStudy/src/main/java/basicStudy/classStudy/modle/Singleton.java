package basicStudy.classStudy.modle;

/**
 * @ClassName Singleton
 * @Description
 * @Author SDY
 * @Date 2023/7/10 22:29
 **/
public class Singleton {
    // 1.私有化构造器
    private Singleton(){

    }
    // 2.内部提供
    // 4.此实例也必须静态化
    private static Singleton single = new Singleton();
    // 3.提供公共的静态的方法，返回当前类的对象
    public static Singleton getInstance(){
        if(single == null){
            single = new Singleton();
        }
        return single;
    }
}
