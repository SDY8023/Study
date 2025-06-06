package OdTest;

import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/8/11 10:16
 * @description
 */
public class Test33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        countAndSay2(sc.nextInt());
    }

    public static String countAndSay(int n) {
        // 先找出行程编码
        String result = "";
        for (int i = 1; i <= n; i++) {
            if(i == 1){
                result = "1";
            }else{
                result = findCode(result);
            }
        }
        System.out.println(result);
        return result;
    }

    public static String findCode(String value){
        StringBuilder result = new StringBuilder();
        String currentData = value.substring(0,1);
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            String substring = value.substring(i, i + 1);
            if(substring.equals(currentData)){
                count++;
            }else{
                result.append(count)
                        .append(currentData);
                currentData = substring;
                count = 1;
            }
        }
        result.append(count)
                .append(currentData);

        return result.toString();
    }

    public static String countAndSay2(int n) {
        String result = findCode2(n);
        System.out.println(result);
        return result;
    }

    public static String findCode2(int n){
        String value = "1";
        if(n > 1){
            value = findCode2(--n);
        }else if(n == 1){
            return "1";
        }
        StringBuilder result = new StringBuilder();
        String currentData = value.substring(0,1);
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            String substring = value.substring(i, i + 1);
            if(substring.equals(currentData)){
                count++;
            }else{
                result.append(count)
                        .append(currentData);
                currentData = substring;
                count = 1;
            }
        }
        result.append(count)
                .append(currentData);
        return result.toString();
    }
}
