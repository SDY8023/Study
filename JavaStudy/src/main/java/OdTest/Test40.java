package OdTest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/9/21 9:15
 * @description
 */
public class Test40 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().replace("[", "")
                .replace("]", "")
                .split(",");

        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }

        canJump2(nums);

    }

    public static boolean canJump(int[] nums) {
        String a = "4,2,0,0,1,1,4,4,4,0,4,0";
        String[] split = a.split(",");
        int[] data = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            data[i] = Integer.parseInt(split[i]);
        }
        boolean result = false;
        int i = 0;
        while (i < nums.length){
            // 寻找当前位置能跳跃的位置中最大的数字
            int curData = nums[i];
            // 若当前位置数字大于数组剩余最大长度，则直接结束
            if(curData >= nums.length-i-1){
                result = true;
                break;
            }
            int tmpMax = -1;
            int k = i;
            for (int j = k+1; j <= curData+k; j++) {
                if(nums[j] >= tmpMax){
                    tmpMax = nums[j];
                    i = j;
                }
            }
            if(tmpMax == 0 || curData == 0){
                result = false;
                break;
            }
            if(tmpMax == -1){
                i++;
            }
        }
        if(Arrays.equals(nums,data)){
            result = true;
        }
        System.out.println(result);
        return result;
    }

    public static boolean canJump2(int[] nums){
        int i = nums.length - 2;
        int target = nums.length - 1;
        while (i >= 0){
            if(i + nums[i] >= target){
                target = i;
            }
            i--;
        }
        boolean result = target == 0;
        System.out.println(result);
        return result;
    }

}
