package SeniorStudy.CompareTest;

/**
 * @ClassName Goods
 * @Description
 * @Author SDY
 * @Date 2023/8/20 9:12
 **/
public class Goods implements Comparable{
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Goods() {
    }

    @Override
    public int compareTo(Object o) {

        if(o instanceof Goods){
            Goods g = (Goods) o;
            if(this.price > g.getPrice()){
                return 1;
            }else if(this.price < g.price){
                return -1;
            }else{
                return 0;
            }
        }
        throw new RuntimeException("输入类型不一致");
    }

    @Override
    public String toString() {
        return "name="+this.name+" price="+this.price;
    }

}
