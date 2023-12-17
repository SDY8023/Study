package genericStudy;

import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2023/10/28 22:23
 **/
public class Test1 {
    public static void main(String[] args) {
        // 使用时不指定类型，
        ArrayList arrayList = new ArrayList();
//        arrayList.add("hello");
        arrayList.add(new Date());
        // 不指定类型不会进行编译检查
        test(arrayList);
       // 使用时指定类型为Object，
        ArrayList<Object> arrayList1 = new ArrayList();
//        arrayList.add("hello");
        arrayList1.add(new Date());

        // 指定类型后，就会进行编译检查
        //test(arrayList1);

    }

    public static void test(ArrayList<String> list){
        String str = "";
        for(String s : list){
            str += s + ",";
        }
        System.out.println("元素:"+str);
    }
}
