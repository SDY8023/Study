package OdTest;

import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/8/4 10:42
 * @description
 */
public class Test27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data1 = sc.next();
        String[] split = data1.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int target = sc.nextInt();
        System.out.println(search(nums,target));

    }

    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target){
                return i;
            }
        }
        return -1;
    }
}
