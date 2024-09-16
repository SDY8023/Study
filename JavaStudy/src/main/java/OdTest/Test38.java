package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test38
 * @Description
 * @Author SDY
 * @Date 2024/9/16 10:24
 **/
public class Test38 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        int n = sc.nextInt();
        myPow(x,n);
    }

    public static double myPow(double x, int n) {
        if(x == 0){
            return x;
        }
        long b = n;
        double result = 1.0;
        if(b < 0){
            x = 1/x;
            b = -b;
        }
        while (b > 0){
            if((b & 1) == 1) result *= x;
            x *= x;
            b >>= 1;
        }
        System.out.println(result);

        return result;
    }
}