package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/11/24 14:09
 * @description
 */
public class Test44 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        test1(n,in);

    }

    public static void test1(int n,Scanner sc){
        List<String> data = new ArrayList<>();
        while (n >= 0){
            String s = sc.nextLine();
            if(s.startsWith("push")){
                String d = s.split(" ")[1];
                data.add(d);
            }else if(s.startsWith("pop")){
                if(data.isEmpty()){
                    System.out.println("error");
                }else{
                    String d = data.get(data.size() - 1);
                    System.out.println(d);
                    data.remove(data.size() - 1);
                }

            }else if(s.startsWith("top")){
                if(data.isEmpty()){
                    System.out.println("error");
                }else{
                    String d = data.get(data.size() - 1);
                    System.out.println(d);
                }

            }
            n--;
        }
    }
}
