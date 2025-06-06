package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test26
 * @Description
 * @Author SDY
 * @Date 2024/7/6 20:57
 **/
public class Test26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        removeDuplicates2(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public static int removeDuplicates(int[] nums) {
        int tmp = nums[0];
        List<Integer> datas = new ArrayList<>();
        datas.add(tmp);
        for (int i = 0; i < nums.length; i++) {
            if(tmp == nums[i]){
                continue;
            }else{
                datas.add(nums[i]);
                tmp = nums[i];
            }
        }
        int[] tmpData = new int[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            nums[i] = datas.get(i);
        }
        nums = tmpData;
        System.out.println(datas);
        return datas.size();

    }

    public static int removeDuplicates2(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            } else {
                j++;
            }
        }
        return i + 1;
    }
}
