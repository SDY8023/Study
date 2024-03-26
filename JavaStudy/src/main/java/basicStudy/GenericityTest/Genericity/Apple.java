package basicStudy.GenericityTest.Genericity;

/**
 * @ClassName Apple
 * @Description
 * @Author SDY
 * @Date 2024/3/14 22:20
 **/
public class Apple<T>{
    private T weight;

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }
}
