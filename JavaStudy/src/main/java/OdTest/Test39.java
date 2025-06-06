package OdTest;

import scala.Int;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Test39
 * @Description
 * @Author SDY
 * @Date 2024/9/16 11:01
 **/
public class Test39 {
    public static void main(String[] args) {

    }

    public List<List<String>> solveNQueens(int n) {


        return null;
    }

    public void getResult(int x, int y, int m,int n, List<Index> tmp,
                          List<List<String>> result){
        // 先写终止条件
        if(x == n-1 && y == n-1 ){
            if(tmp.size() == n){
                // 拼凑结果
                tmp.sort(((o1, o2) -> (o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y)));

            }
            return;
        }



    }

    public class Index {
        int x = 0;
        int y = 0;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
