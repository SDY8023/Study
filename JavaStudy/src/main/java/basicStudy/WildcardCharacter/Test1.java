package basicStudy.WildcardCharacter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/3/14 22:04
 **/
public class Test1 {
    public static void main(String[] args) {
        List<?> list = null;
        list = new ArrayList<String>();
        list = new ArrayList<Double>();
        //list.add(3);
        list.add(null);


    }
}
