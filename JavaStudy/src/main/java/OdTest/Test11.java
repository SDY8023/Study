package OdTest;

import scala.Int;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName Test11
 * @Description
 * @Author SDY
 * @Date 2024/5/19 12:21
 **/
public class Test11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.next().split(",");
        int[] height = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            height[i] = Integer.parseInt(data[i]);
        }
        System.out.println(maxArea2(height));

    }

    public static int maxArea(int[] height){
        int result = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                int tmp = (j - i) * Math.min(height[i],height[j]);
                if(result < tmp ){
                    result = tmp;
                }
            }
        }
        return result;

    }

    public static int maxArea2(int[] height){
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right){
            int h = Math.min(height[left],height[right]);
            int temp = (right- left) * h;
            res = Math.max(res,temp);
            if(height[left] < height[right]){
                left ++;
            }else{
                right --;
            }
        }
        return res;
    }
}
