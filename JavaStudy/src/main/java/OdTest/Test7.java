package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test7
 * @Description
 * @Author SDY
 * @Date 2024/5/15 20:47
 **/
public class Test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        reverse2(sc.nextInt());
    }

    public static int reverse(int x){
        String d = String.valueOf(x);
        StringBuilder stringBuilder = new StringBuilder();
        String pre = "";
        for (int i = 0; i < d.length(); i++) {
            if(d.charAt(i) == '-'){
                pre = d.substring(i,i+1);
            }else{
                stringBuilder.append(d.charAt(i));
            }
        }
        stringBuilder.reverse();
        if(Long.parseLong(stringBuilder.toString()) > Integer.MAX_VALUE){
            return 0;
        }
        int result = Integer.parseInt(pre + stringBuilder);
//        System.out.println(result);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
        //2147483647
        //-2147483648

        return result;
    }

    public static int reverse2(int x){
        int result = 0;
        while (x != 0){
            int pop = x % 10;
            if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE && pop > 7)){
                result = 0;
                break;
            }
            if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE && pop < -8)){
                result = 0;
                break;
            }
            result = result * 10 + pop;
            x /= 10;
        }
        System.out.println(result);
        return result;
    }
}
