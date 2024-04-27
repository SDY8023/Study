package OdTest;

import java.util.Arrays;

/**
 * @ClassName Test5
 * @Description
 * @Author SDY
 * @Date 2024/4/27 20:49
 **/
public class Test5 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{3,4,5,6};
        int[] nums2 = new int[]{3,4};
        System.out.println(findMedianSortedArrays(nums1,nums2));

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int[] dataList = new int[nums1.length + nums2.length];
        System.arraycopy(nums1,0,dataList,0,nums1.length);
        System.arraycopy(nums2,0,dataList,nums1.length,nums2.length);

        Arrays.sort(dataList);
        int len = dataList.length;
        double result = 0;
        if(len % 2 == 0){
            result = (Double.parseDouble(String.valueOf(dataList[len / 2])) + Double.parseDouble(String.valueOf(dataList[(len / 2)-1]))) / 2;
        }else{
            result = dataList[len / 2];
        }

        return result;
    }
}
