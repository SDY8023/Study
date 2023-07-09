package basicStudy.classStudy.childClass;

/**
 * @ClassName Wolf
 * @Description
 * @Author SDY
 * @Date 2023/7/9 21:41
 **/
public class Wolf extends Animal{
    public Wolf(){
        super("灰太狼", 3);
        System.out.println("Wolf无参数的构造器");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
