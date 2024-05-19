package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test9
 * @Description
 * @Author SDY
 * @Date 2024/5/19 9:28
 **/
public class Test9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(isPalindrome(sc.nextInt()));

    }

    /**
     * 回文数判断
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x){
        if(x < 0){
            return false;
        }
        int d = x;
        int result = 0;
        while (d != 0){
            int y = d % 10;
            result = result * 10 + y;
            d /= 10;
        }
        return x == result;
    }


}
