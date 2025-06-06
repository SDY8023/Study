package OdTest;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * @ClassName Test13
 * @Description
 * @Author SDY
 * @Date 2024/6/12 22:50
 **/
public class Test13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        romanToInt(sc.next());

    }

    public static int romanToInt(String s) {
        HashMap<String, Integer> dataMap = new HashMap<>();
        dataMap.put("I",1);
        dataMap.put("V",5);
        dataMap.put("X",10);
        dataMap.put("L",50);
        dataMap.put("C",100);
        dataMap.put("D",500);
        dataMap.put("M",1000);
        dataMap.put("IV",4);
        dataMap.put("IX",9);
        dataMap.put("XL",40);
        dataMap.put("XC",90);
        dataMap.put("CD",400);
        dataMap.put("CM",900);

        int length = s.length();
        int i = 0;
        int result = 0;
        Set<String> keySet = dataMap.keySet();
        while (i < length){
            String d1 = s.substring(i, i + 1);
            if(i == length -1){
                result += dataMap.get(d1);
            }else{
                String d2 = s.substring(i, i + 2);
                if(keySet.contains(d2)){
                    result += dataMap.get(d2);
                    i += 2;
                    continue;
                }else{
                    result += dataMap.get(d1);
                }
            }
            i++;
        }
        System.out.println(result);
        return result;
    }
}
