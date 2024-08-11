package OdTest;

import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/8/4 10:53
 * @description
 */
public class Test28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data1 = sc.next();
        String[] split = data1.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int target = sc.nextInt();
        int[] ints = searchRange(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target){
                if(j == 0){
                    result[j] = i;
                }else{
                    result[1] = i;
                }
                j++;
            }
        }
        result[1] = result[1] == -1 ? result[0] : result[1];
        return result;
    }
}
