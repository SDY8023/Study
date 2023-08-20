package basicStudy.arrayStudy;

import java.util.Random;

/**
 * @ClassName test1
 * @Description 多维数组练习
 * @Author SDY
 * @Date 2023/7/8 21:47
 **/
public class test1 {
    public static void main(String[] args) {
        testArray1();

    }

    private static void testArray1(){
        int[] arr1 = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int d = random.nextInt(89)+10;
            arr1[i] = d;
            System.out.println(d);
        }
        int max = 0;
        int min = 100;
        int sum = 0;
        int average = 0;
        // 求最大值
        for (int i : arr1) {
            max = Math.max(i, max);
            min = Math.min(i,min);
            sum += i;
        }
        average = sum / 10 ;
        System.out.println("max:"+max+"\nmin:"+min+"\nsum:"+sum+"\naverage:"+average);

    }
}
