package OdTest;

import scala.Int;

import java.util.*;

/**
 * @author 18438
 * @date 2024/10/14 11:55
 * @description
 */
public class Test42 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String firstData = sc.next();
//        String[] firstArray = firstData.split(" ");
        int n = sc.nextInt();
        int e = sc.nextInt();
        int[][] param = new int[e+1][2];
        int[] first = new int[2];
        first[0] = n;
        first[1] = e;
        param[0] = first;
        for (int i = 1; i <= e; i++) {
//            String[] data = sc.next().split(" ");
            int[] dataArray = new int[2];
            dataArray[0] = sc.nextInt();
            dataArray[1] = sc.nextInt();
            param[i] = dataArray;
        }

        earth(param);

    }

    public static void earth(int[][] data){
        int[] firstData = data[0];
        int n = firstData[0];
        // k 发动机编号，v 启动顺序
        Map<Integer, List<Integer>> readyData = new HashMap<>();
        for (int i = 0; i < firstData[0]; i++) {
            readyData.put(i,null);
        }
        for (int i = 1; i < data.length; i++) {
            List<Integer> integers = new ArrayList<>();
            integers.add(data[i][0]);
            readyData.put(data[i][1],integers);
            startData(readyData,n,data[i][1],data[i][0]);
        }
        System.out.println(readyData);

    }

    public static void startData(Map<Integer, List<Integer>> readyData,int n,int startIndex,int startSort){
        if(startIndex < 0 || startIndex >= n){
            return;
        }
        List<Integer> integers = readyData.get(startIndex);
        // 再启动周边的
        if(integers == null){
            integers = new ArrayList<>();
        }
        integers.add(startSort);
        readyData.put(startIndex,integers);
        int left = startIndex;
        int right = startIndex;
        int leftSort = startSort;
        int rightSort = startSort;
        startData(readyData,n,right+1,rightSort+1);
        startData(readyData,n,left-1,leftSort+1);
    }

}
