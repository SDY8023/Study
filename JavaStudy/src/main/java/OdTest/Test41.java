package OdTest;

import java.util.*;

/**
 * @author 18438
 * @date 2024/9/21 10:14
 * @description
 */
public class Test41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split("],");
        int[][] matrix = new int[split.length][split[0].split(",").length];
        for (int i = 0; i < split.length; i++) {
            int[] matrix1 = matrix[i];
            String[] split1 = split[i].replace("[", "")
                    .replace("]", "")
                    .split(",");
            for (int j = 0; j < split1.length; j++) {
                matrix1[j] = Integer.parseInt(split1[j]);
            }
        }

        merge(matrix);

    }

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] tmp1 = intervals[i];
            int start = tmp1[0];
            int end = tmp1[1];
            for (int j = i+1; j < intervals.length; j++) {
                int[] tmp2 = intervals[j];
                if(tmp2[0] > end){
                    i = j-1;
                    break;
                }
                end = Math.max(tmp2[1],end);
                start = Math.min(start,tmp2[0]);
                if(j == intervals.length - 1){
                    i = j;
                }
            }
            result.add(Arrays.asList(tmp1[0],end));
        }
        int[][] datas = new int[result.size()][result.get(0).size()];
        for (int i = 0; i < result.size(); i++) {
            List<Integer> integers = result.get(i);
            int[] tmpData = new int[integers.size()];
            for (int j = 0; j < integers.size(); j++) {
                tmpData[j] = integers.get(j);
            }
            datas[i] = tmpData;
        }
        System.out.println(result);
        return datas;
    }
}
