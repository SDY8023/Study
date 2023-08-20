package SeniorStudy.StringTest;

/**
 * @ClassName test1
 * @Description
 * @Author SDY
 * @Date 2023/8/6 22:01
 **/
public class test1 {
    String str = new String("good");
    char[] ch = {'t','e','s','t'};

    public void change(String str,char ch[]){
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        test1 ex = new test1();
        ex.change(ex.str,ex.ch);
        System.out.println(ex.str + " and");
        System.out.println(ex.ch);
    }
}
