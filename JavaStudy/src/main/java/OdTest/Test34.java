package OdTest;

import akka.actor.ScalaActorRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test34
 * @Description
 * @Author SDY
 * @Date 2024/9/4 21:11
 **/
public class Test34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().replace("[", "")
                .replace("]", "")
                .split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        permute(nums);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        dfs(nums,result,data);
        System.out.println(result);
        return result;
    }

    public static void dfs(int[] nums,List<List<Integer>> result,List<Integer> data){
        // 长度一致 表明元素已经遍历完
        if(nums.length == data.size()){
            result.add(new ArrayList<>(data));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 包含继续寻找下一个元素
            if(data.contains(nums[i])) continue;
            data.add(nums[i]);
            // 递归继续
            dfs(nums,result,data);
            data.remove(data.size()-1);
        }
    }


}
