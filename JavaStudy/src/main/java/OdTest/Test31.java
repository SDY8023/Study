package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test31
 * @Description
 * @Author SDY
 * @Date 2024/8/5 21:15
 **/
public class Test31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        String[] split = next.split("],");
        char[][] board = new char[split.length][split.length];
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(",");
            for (int j = 0; j < split1.length; j++) {
                board[i][j] = split1[j].replace("\"","")
                        .replace("[","")
                        .replace("]","")
                        .charAt(0);
            }
        }
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean result = true;

        for (int i = 0; i < board.length; i++) {
            // 每行遍历
            List<String> lineList = new ArrayList<>();
            List<String> rowList = new ArrayList<>();
            for (int j = 0; j < board.length; j++) {
                char l = board[i][j];
                char r = board[j][i];
                if(!String.valueOf(l).equals(".")){
                    if(lineList.contains(String.valueOf(l))){
                        return false;
                    }else{
                        lineList.add(String.valueOf(l));
                    }
                }

                if(!String.valueOf(r).equals(".")){
                    if(rowList.contains(String.valueOf(r))){
                        return false;
                    }else{
                        rowList.add(String.valueOf(r));
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<String> line2List = new ArrayList<>();
                for (int k = 0; k <3; k++) {
                    int x = i * 3 + k;
                    for (int l = 0; l < 3; l++) {
                        int y = j * 3 + l;
                        char data = board[x][y];
                        if(line2List.contains(String.valueOf(data)) && !String.valueOf(data).equals(".")){
                            return false;
                        }
                        line2List.add(String.valueOf(data));
                    }
                }
            }
        }
        return result;
    }
}
