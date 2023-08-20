package SeniorStudy.CompareTest;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName Test2
 * @Description
 * @Author SDY
 * @Date 2023/8/20 16:12
 **/
public class Test2 {
    public static void main(String[] args) {
        Goods[] all = new Goods[4];
        all[0] = new Goods("War and Peace",100);
        all[1] = new Goods("Childhood",80);
        all[2] = new Goods("Scarlet and Black",140);
        all[3] = new Goods("Notre Dame de Paris",120);

        Arrays.sort(all, (o1, o2) -> o1.compareTo(o2));
        System.out.println(Arrays.toString(all));

    }
}
