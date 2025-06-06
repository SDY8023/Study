package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test29
 * @Description
 * @Author SDY
 * @Date 2024/7/11 11:29
 **/
public class Test29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d1 = sc.nextInt();
        int d2 = sc.nextInt();
        divide1(d1,d2);

    }

    public static int divide(int dividend, int divisor) {
        int r = 0;
        if(dividend == -2147483648){
            r = 1;
            dividend += Math.abs(divisor);
        }
        if(dividend > 0 && divisor > 0){
            r += cal(Math.abs(dividend),Math.abs(divisor));
        }else if(dividend < 0 && divisor > 0){
            r = -r + -cal(Math.abs(dividend),divisor);
        }else if(dividend > 0 && divisor < 0){
            r = -r + -cal(dividend,Math.abs(divisor));
        }else if(dividend < 0 && divisor < 0){
            r = r + cal(Math.abs(dividend),Math.abs(divisor)) == -2147483648 ? 2147483647 : r + cal(Math.abs(dividend),Math.abs(divisor));
        }
        System.out.println(r);
        return r;
    }

    public static int divide1(int dividend, int divisor){
        boolean sign = (dividend > 0) ^ (divisor > 0);
        int result = 0;
        if(dividend>0) {
            dividend = -dividend;
        }
        if(divisor>0) divisor = -divisor;
        while(dividend <= divisor) {
            int temp_result = -1;
            int temp_divisor = divisor;
            while(dividend <= (temp_divisor << 1)) {
                if(temp_divisor <= (Integer.MIN_VALUE >> 1))break;
                temp_result = temp_result << 1;
                temp_divisor = temp_divisor << 1;
            }
            dividend = dividend - temp_divisor;
            result += temp_result;
        }
        if(!sign) {
            if(result <= Integer.MIN_VALUE) return Integer.MAX_VALUE;
            result = - result;
        }
        return result;
    }

    public static int cal(int dividend, int divisor){
        int a = 0;
        while (dividend >= divisor){
            a++;
            dividend -= divisor;
        }
        return a;
    }
}
