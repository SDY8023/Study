package SeniorStudy.CollectionTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2023/8/21 22:08
 **/
public class Test1 {

    @Test
    public void testListRemove(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);
    }

    private static void updateList(List list){
        list.remove(2);
    }
}
