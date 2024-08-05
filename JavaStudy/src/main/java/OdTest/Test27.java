package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test27
 * @Description
 * @Author SDY
 * @Date 2024/7/9 20:17
 **/
public class Test27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.next().split(",");

        int[] nums = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            nums[i] = Integer.parseInt(data[i]);
        }
        removeElement(nums,sc.nextInt());
    }

    public static int removeElement(int[] nums, int val) {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                data.add(nums[i]);
            }
        }
        for (int i = 0; i < data.size(); i++) {
            nums[i] = data.get(i);
        }
        System.out.println(data.size());
        for (int num : nums) {
            System.out.println(num);
        }
        return data.size();
    }
}
