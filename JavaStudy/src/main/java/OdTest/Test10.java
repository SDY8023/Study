package OdTest;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Test10
 * @Description
 * @Author SDY
 * @Date 2024/5/19 9:37
 **/
public class Test10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(isMatch(sc.next(),sc.next()));

    }

    public static boolean isMatch(String s, String p){
        boolean result = true;
        int sLen = s.length();
        String tmp = "";
        int m = 0;
        String currP = "";
        for (int i = 0; i < sLen; i++) {
            if(i >= p.length()){
                currP = "";
            }else{
                currP = p.substring(i,i+1);
            }
            if((!currP.equals("*") || !currP.equals(".")) && currP.equals(s.substring(i,i+1)) ){
                continue;
            }
            if(currP.equals("*")){
                if(i == 0){
                    result = false;
                    break;
                }
                if(!s.substring(0,i).equals(p.substring(0,i))){
                    result = false;
                    break;
                }
                // 拿到上一位元素
                tmp = p.substring(i-1,i);
                while (m < sLen){
                    if(tmp.equals(s.substring(m,m+1))){
                        m++;
                        continue;
                    }else{
                        break;
                    }
                }
            }

            if(currP.equals(".")){
                m++;
            }

        }

        return result;
    }
}
