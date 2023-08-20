package basicStudy.classStudy.childClass;

/**
 * @ClassName Animal
 * @Description
 * @Author SDY
 * @Date 2023/7/9 21:39
 **/
public class Animal {
    public Animal(String name){
        System.out.println("Animal的带1个参数的构造器 name:"+name);
    }
    public Animal(String name,int age){
        this(name);
        System.out.println("Animal带两个参数的构造器，其age为" + age);
    }
}
