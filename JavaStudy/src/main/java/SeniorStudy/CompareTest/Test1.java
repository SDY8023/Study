package SeniorStudy.CompareTest;

import java.util.Arrays;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2023/8/20 9:13
 **/
public class Test1 {
    public static void main(String[] args) {
        Goods[] goods = new Goods[4];
        goods[0] = new Goods("《红楼梦》",100);
        goods[1] = new Goods("《西游记》",80);
        goods[2] = new Goods("《三国演义》",140);
        goods[3] = new Goods("《水浒传》",120);

        System.out.println(Arrays.toString(goods));

        Arrays.sort(goods);

        System.out.println(Arrays.toString(goods));

    }
}
