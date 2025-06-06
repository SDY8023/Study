package OdTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test35
 * @Description
 * @Author SDY
 * @Date 2024/9/4 21:37
 **/
public class Test35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().replace("[", "")
                .replace("]", "")
                .split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        permuteUnique(nums);
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        dfs(nums,result,data);
        System.out.println(result);
        return result;
    }

    public static void dfs(int[] nums,List<List<Integer>> result,List<Integer> data){
        if(nums.length == data.size()){
            result.add(new ArrayList<>(data));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if((i > 0 && nums[i] == nums[i-1]) || nums[i] == 100) continue;
            int temp = nums[i];
            data.add(nums[i]);
            nums[i] = 100;
            dfs(nums,result,data);
            nums[i] = temp;
            data.remove(data.size()-1);
        }
    }

    public static boolean check(List<List<Integer>> result,List<Integer> data){
        for (List<Integer> dataList : result) {
            int count = 0;
            for (int i = 0; i < dataList.size(); i++) {
                if(dataList.get(i) == data.get(i)) count++;
            }
            if(count == data.size()){
                return false;
            }
        }
        return true;
    }
}
