package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test22
 * @Description
 * @Author SDY
 * @Date 2024/6/24 22:23
 **/
public class Test22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        generateParenthesis(sc.nextInt());
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        recur(result,"",0,0,n);
        System.out.println(result);
        return result;

    }

    private static void recur(List<String> ans,String cur,int open,int close,int max){
        System.out.println(cur);
        if(cur.length() == 2* max){
            ans.add(cur);
            return;
        }

        if(open < max){
            recur(ans,cur+"(",open+1,close,max);
        }

        if(close < open){
            recur(ans,cur+")",open,close+1,max);
        }
    }


}
