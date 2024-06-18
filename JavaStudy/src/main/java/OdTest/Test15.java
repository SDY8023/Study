package OdTest;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @ClassName Test15
 * @Description
 * @Author SDY
 * @Date 2024/6/13 22:25
 **/
public class Test15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split(",");
        int[] data = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            data[i] = Integer.parseInt(split[i]);
        }
        threeSum2(data);

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    int temp = nums[i] + nums[j] + nums[k];
                    if(temp == 0){
                        List<Integer> integers = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(integers);
                        if(checkRetry(result,integers)){
                            result.add(integers);
                        }
                    }
                }
            }
        }
        System.out.println(result);
        return result;
    }

    private static boolean checkRetry(List<List<Integer>> result, List<Integer> integers){
        boolean checkResult = true;
        for (List<Integer> integerList : result) {
            if(integerList.equals(integers)){
                checkResult = false;
            }
        }
        return checkResult;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        if(nums == null || len < 3) return result;
        for (int i = 0; i < len; i++) {
            if(nums[i] > 0){
                break;
            }
            if( i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int L = i+1;
            int R = len-1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L < R && nums[L] == nums[L+1]) L++;
                    while (L < R && nums[R] == nums[R-1]) R--;
                    L++;
                    R--;
                }else if(sum < 0 ){
                    L++;
                }else if(sum > 0){
                    R--;
                }
            }
        }
        System.out.println(result);
        return result;
    }
}



























