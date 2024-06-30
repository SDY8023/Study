package OdTest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.flink.util.CollectionUtil;

import java.util.*;

/**
 * @author 18438
 * @date 2024/6/23 10:21
 * @description
 */
public class Test20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(isValid(sc.next()));

    }

    public static boolean isValid(String s) {
        Map<String, String> data = new HashMap<>();
        data.put("{","}");
        data.put("[","]");
        data.put("(",")");
        int len = s.length();
        boolean result = true;
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            String left = s.substring(i, i + 1);
            if(left.equals("{") || left.equals("[") || left.equals("(")){
                // 进对
                dataList.add(left);
            }else{
                // 出队
                if(dataList.isEmpty()){
                    result = false;
                    break;
                }else{
                    result = data.get(dataList.get(dataList.size()-1)).equals(left);
                    if(!result){
                        break;
                    }else{
                        dataList.remove(dataList.size()-1);
                    }
                }
            }

        }
        // 若最后队列中还剩余有，说明不是完全对照，返回false
        if(!dataList.isEmpty()){
            result = false;
        }
        return result;
    }
}
