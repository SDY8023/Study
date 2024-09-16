package OdTest;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.protocol.types.Field;

import java.util.*;

/**
 * @ClassName Test37
 * @Description
 * @Author SDY
 * @Date 2024/9/12 20:56
 **/
public class Test37 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String d = sc.nextLine();
        String datas = d.replace("[", "")
                .replace("]", "")
                .replace("\"", "").replace(" ","");
        String[] data = datas.split(",");

        System.out.println(groupAnagrams2(data));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        boolean[] booleans = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String d = strs[i];
            if(booleans[i]) continue;
            List<String> dataList = new ArrayList<>();
            dataList.add(d);
            booleans[i] = true;
            String d1 = sortStr(d);
            for (int j = i+1; j < strs.length; j++) {
                if(booleans[j]) continue;
                String data = strs[j];
                if(d1.equals(sortStr(data))){
                    dataList.add(data);
                    booleans[j] = true;
                }
            }
            result.add(dataList);
        }
        System.out.println(result);
        return result;
    }

    public static String sortStr(String str){
        List<String> dList = new ArrayList<>();
        for (int j = 0; j < str.length(); j++) {
            dList.add(str.substring(j,j+1));
        }
        Collections.sort(dList);
        return dList.toString();
    }

    public static List<List<String>> groupAnagrams2(String[] strs){
        Map<String, List<String>> hashData = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            if(hashData.containsKey(s)){
                hashData.get(s).add(strs[i]);
            }else{
                List<String> strings = new ArrayList<>();
                strings.add(strs[i]);
                hashData.put(s,strings);
            }
        }
        return new ArrayList<List<String>>(hashData.values());
    }

}
