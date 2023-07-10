package basicStudy.classStudy.childClass;

/**
 * @ClassName Person
 * @Description
 * @Author SDY
 * @Date 2023/7/10 22:17
 **/
public class Person {
    private int id;
    public static int total = 0;
    public Person(){
        total++;
        id = total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
