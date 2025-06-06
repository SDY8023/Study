package OdTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName Test18
 * @Description
 * @Author SDY
 * @Date 2024/6/22 13:51
 **/
public class Test18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dataArray = Arrays.stream(sc.next().split(",")).mapToInt(Integer::parseInt).toArray();
        String target = sc.next();
        fourSum(dataArray,Integer.parseInt(target));

    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        long tmpData = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length - 2; j++) {
                int m = j+1;
                int n = nums.length-1;
                while (m < n){
                    tmpData = Long.parseLong(String.valueOf(nums[i])) +
                            Long.parseLong(String.valueOf(nums[j])) +
                            Long.parseLong(String.valueOf(nums[m])) +
                            Long.parseLong(String.valueOf(nums[n]));
                    if(tmpData == target){
                        List<Integer> tmp = Arrays.asList(nums[i],nums[j],nums[m],nums[n]);
                        result.add(tmp);
                        n--;
                    }else if(tmpData < target){
                        m++;
                    }else{
                        n--;
                    }
                }
            }
        }

        System.out.println(result);
        return result.parallelStream().collect(Collectors.toList());

    }


}
