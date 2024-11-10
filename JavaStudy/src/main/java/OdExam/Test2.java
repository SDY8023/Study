package OdExam;

import java.util.*;

/**
 * @author 18438
 * @date 2024/10/21 11:41
 * @description
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> data = new ArrayList<>();
        String next = sc.next();
        String[] split = next.split(",");
        for (int i = 0; i < split.length; i++) {
            data.add(Integer.parseInt(split[i]));
        }
        int maxResult = -1;
        for (int i = 0; i < data.size()-1; i++) {
            for (int j = i+1; j < data.size(); j++) {
                int high = Math.min(data.get(i),data.get(j));
                int len = j - i;
                int d = high * len;
                maxResult = Math.max(d, maxResult);
            }
        }
        System.out.println(maxResult);
    }
}
