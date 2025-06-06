package SeniorStudy.CollectionTest.SetTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName Test2
 * @Description
 * @Author SDY
 * @Date 2023/8/28 21:21
 **/
public class Test2 {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object o : list2) {
            System.out.println(o);
        }

    }

    public static List duplicateList(List list){
        HashSet set = new HashSet<>();
        set.addAll(list);
        return new ArrayList(set);
    }


}
