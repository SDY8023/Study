package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test28
 * @Description
 * @Author SDY
 * @Date 2024/7/9 20:28
 **/
public class Test28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(strStr(sc.next(),sc.next()));
    }

    public static int strStr(String haystack, String needle) {
        if(!haystack.contains(needle)){
            return -1;
        }

        int i = 0;
        int result = -1;
        while (i < haystack.length()){
            String substring = haystack.substring(i, i + needle.length());
            if(substring.equals(needle)){
                result = i;
                break;
            }
            i += 1;
        }
        return result;
    }
}
