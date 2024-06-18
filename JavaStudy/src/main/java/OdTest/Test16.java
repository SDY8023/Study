package OdTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName Test16
 * @Description
 * @Author SDY
 * @Date 2024/6/16 14:58
 **/
public class Test16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split(",");
        int target = sc.nextInt();
        int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        threeSumClosest(ints,target);


    }

    public static int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int len = nums.length;
        if(len == 3) return Arrays.stream(nums).sum();
        Arrays.sort(nums);
        // 差值
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            // 排重
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i+1;
            int R = len - 1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(Math.abs(sum - target) < temp){
                    result = sum;
                    temp = Math.abs(sum - target);
                }
                if(sum > target){
                    R--;
                }else if(sum < target){
                    L++;
                }else {
                    break;
                }

            }
            // 已找到最优解
            if(temp == 0){
                break;
            }

        }
        System.out.println(result);
        return result;
    }


}
