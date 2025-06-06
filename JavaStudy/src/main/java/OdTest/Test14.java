package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test14
 * @Description
 * @Author SDY
 * @Date 2024/6/13 22:04
 **/
public class Test14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        longestCommonPrefix(sc.next().split(","));

    }

    public static String longestCommonPrefix(String[] strs) {
        String minStr = strs[0];
        for (String str : strs) {
            if(minStr.length() > str.length()){
                minStr = str;
            }
        }
        String result = "";
        boolean isContain = true;
        for (int i = 0; i < minStr.length(); i++) {
            String suffix = minStr.substring(0,i+1);
            for (String str : strs) {
                if(!str.startsWith(suffix)){
                    isContain = false;
                    break;
                }
            }
            if(isContain){
                result = suffix;
            }else{
                break;
            }
        }
        System.out.println(result.toString());
        return result.toString();

    }
}
