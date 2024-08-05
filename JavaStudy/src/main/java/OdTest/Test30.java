package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test30
 * @Description
 * @Author SDY
 * @Date 2024/8/5 20:57
 **/
public class Test30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split(",");
        int[] data = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            data[i] = Integer.parseInt(split[i]);
        }
        searchInsert(data,sc.nextInt());

    }

    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int result = len;
        if(nums[0] >= target){
            return 0;
        }else if(nums[len-1] == target){
            return len-1;
        }
        for (int i = 0; i < len-1; i++) {
            if(nums[i] < target && nums[i+1] > target){
                result = i+1;
                break;
            }else if(nums[i] == target){
                result = i;
            }
        }
        System.out.println(result);
        return result;
    }
}
