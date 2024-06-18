package OdTest;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @ClassName Test17
 * @Description
 * @Author SDY
 * @Date 2024/6/18 22:01
 **/
public class Test17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        letterCombinations2(sc.next());


    }

    public static List<String> letterCombinations(String digits) {
        HashMap<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("2", Arrays.asList("a","b","c"));
        dataMap.put("3", Arrays.asList("d","e","f"));
        dataMap.put("4", Arrays.asList("g","h","i"));
        dataMap.put("5", Arrays.asList("j","k","l"));
        dataMap.put("6", Arrays.asList("m","n","o"));
        dataMap.put("7", Arrays.asList("p","q","r","s"));
        dataMap.put("8", Arrays.asList("t","u","v"));
        dataMap.put("9", Arrays.asList("w","x","y","z"));

        List<String> result = new ArrayList<>();
        if(digits.equals("")){
            return result;
        }
        List<List<String>> lists = new ArrayList<>();
        int len = digits.length();
        for (int i = 0; i < len; i++) {
            String data = digits.substring(i, i + 1);
            List<String> dataList = dataMap.getOrDefault(data, new ArrayList<String>());
            lists.add(dataList);
        }
        int size = lists.size();
        if(size == 1) {
            result.addAll(lists.get(0));
        }else if(size == 2){
            List<String> firstList = lists.get(0);
            List<String> secondList = lists.get(1);
            for (int i = 0; i < firstList.size(); i++) {
                for (int j = 0; j < secondList.size(); j++) {
                    result.add(firstList.get(i)+secondList.get(j));
                }
            }
        }else if(size == 3){
            List<String> firstList = lists.get(0);
            List<String> secondList = lists.get(1);
            List<String> thirdList = lists.get(2);
            for (int i = 0; i < firstList.size(); i++) {
                for (int j = 0; j < secondList.size(); j++) {
                    for (int k = 0; k < thirdList.size(); k++) {
                        result.add(firstList.get(i)+secondList.get(j)+thirdList.get(k));
                    }
                }
            }
        }else if(size == 4){
            List<String> firstList = lists.get(0);
            List<String> secondList = lists.get(1);
            List<String> thirdList = lists.get(2);
            List<String> fouthList = lists.get(3);
            for (int i = 0; i < firstList.size(); i++) {
                for (int j = 0; j < secondList.size(); j++) {
                    for (int k = 0; k < thirdList.size(); k++) {
                        for (int m = 0; m < fouthList.size(); m++) {
                            result.add(firstList.get(i)+secondList.get(j)+thirdList.get(k)+fouthList.get(m));
                        }
                    }
                }
            }

        }


        System.out.println(result);

        return result;
    }

    public static List<String> letterCombinations2(String digits){
        HashMap<String, List<String>> dataMap = new HashMap<>();
        dataMap.put("2", Arrays.asList("a","b","c"));
        dataMap.put("3", Arrays.asList("d","e","f"));
        dataMap.put("4", Arrays.asList("g","h","i"));
        dataMap.put("5", Arrays.asList("j","k","l"));
        dataMap.put("6", Arrays.asList("m","n","o"));
        dataMap.put("7", Arrays.asList("p","q","r","s"));
        dataMap.put("8", Arrays.asList("t","u","v"));
        dataMap.put("9", Arrays.asList("w","x","y","z"));

        List<String> result = new ArrayList<>();
        if(digits.equals("")){
            return result;
        }
        List<List<String>> lists = new ArrayList<>();
        int len = digits.length();
        for (int i = 0; i < len; i++) {
            String data = digits.substring(i, i + 1);
            List<String> dataList = dataMap.getOrDefault(data, new ArrayList<String>());
            if(i == 0){
                result = dataList;
            }else{
                result = getData(dataList,result);
            }

        }

        System.out.println(result);
        return result;
    }

    public static List<String> getData(List<String> dataList,List<String> result){
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < dataList.size(); j++) {
                resultList.add(result.get(i)+dataList.get(j));
            }
        }
        return resultList;

    }


}
