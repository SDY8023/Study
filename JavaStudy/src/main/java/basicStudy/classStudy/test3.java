package basicStudy.classStudy;

/**
 * @ClassName test3
 * @Description
 * @Author SDY
 * @Date 2023/7/10 21:37
 **/
public class test3 {
    public static void main(String[] args) {
        Object o1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(o1);

        Object o2;
        if(true){
            o2 = new Integer(1);
        }else{
            o2 = new Double(2.0);
        }
        System.out.println(o2);
    }
}
