package OdTest;

import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/10/21 10:42
 * @description
 */
public class Test43 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
