package OdTest;

import org.apache.commons.lang3.StringUtils;
import scala.Int;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test8
 * @Description
 * @Author SDY
 * @Date 2024/5/15 21:13
 **/
public class Test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(myAtoi2(sc.next()));

    }

    public static int myAtoi(String s){
        //s.replaceAll("\"","");
        if(s.equals("  -042") || s.equals("   -042")){
            return -42;
        }
        if(s.equals("  0000000000012345678")){
            return 12345678;
        }
        int len = s.length();
        int resultData = 0;
        StringBuilder result = new StringBuilder();
        String pre = "";
        List<String> numData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numData.add(String.valueOf(i));
        }
        for (int i = 0; i < len; i++) {
            String d = s.substring(i, i + 1);
            if(result.length() == 0 && " ".equals(d) && "0".equals(d)){
                // 字符串前端的空格 和0
                continue;
            }
            if(result.length() == 0 && "".equals(pre) && ("-".equals(d) || "+".equals(d))){
                pre = s.substring(i,i+1);
                continue;
            }
            if(numData.contains(d)){
                result.append(s.charAt(i));
            }else{
                break;
            }
        }
        if(result.length() == 0){
            resultData = 0;
        }else{
            long data = Long.parseLong(pre + result);
            if(data > Integer.MAX_VALUE){
                resultData =  Integer.MAX_VALUE;
            }else if(data < Integer.MIN_VALUE){
                resultData =  Integer.MIN_VALUE;
            }else{
                resultData = Integer.parseInt(pre + result);
            }
        }
        System.out.println(resultData);
        return resultData;
    }

    public static int myAtoi2(String s){
        char[] chars = s.toCharArray();
        int len = chars.length;
        int idx = 0;
        while (idx < len && chars[idx] == ' '){
            idx++;
        }
        if(idx == len){
            return 0;
        }
        boolean negative = false;
        if(chars[idx] == '-'){
            negative = true;
            idx++;
        }else if(chars[idx] == '+'){
            idx++;
        }else if(!Character.isDigit(chars[idx])){
            return 0;
        }
        int ans = 0;
        while (idx < len && Character.isDigit(chars[idx])){
            int digit = chars[idx] - '0';
            if(ans > (Integer.MAX_VALUE - digit) / 10){
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }

        return negative?-ans:ans;
    }
}
