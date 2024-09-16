package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test36
 * @Description
 * @Author SDY
 * @Date 2024/9/8 17:47
 **/
public class Test36 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split("],");
        int[][] matrix = new int[split.length][split.length];
        for (int i = 0; i < split.length; i++) {
            int[] matrix1 = matrix[i];
            String[] split1 = split[i].replace("[", "")
                    .replace("]", "")
                    .split(",");
            for (int j = 0; j < split1.length; j++) {
                matrix1[j] = Integer.parseInt(split1[j]);
            }
        }
        rotate(matrix);

    }


    public static void rotate(int[][] matrix) {
        int len = matrix[0].length;
        int[][] result = new int[matrix.length][len];
        for (int i = 0; i < matrix.length; i++) {
            int[] line = result[i];
            for (int j = matrix.length - 1; j > -1; j--) {
                line[matrix.length - 1 - j] = matrix[j][i];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            int[] m = matrix[i];
            int[] d = result[i];
            for (int j = 0; j < d.length; j++) {
                m[j] = d[j];
            }
        }

    }
}
