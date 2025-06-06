package OdExam;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author 18438
 * @date 2024/10/21 10:45
 * @description
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        List<String> sampleData = new ArrayList<>();
        sampleData.add("0");
        sampleData.add("1");
        sampleData.add("2");
        sampleData.add("3");
        sampleData.add("4");
        sampleData.add("5");
        sampleData.add("6");
        sampleData.add("7");
        sampleData.add("8");
        sampleData.add("9");
        sampleData.add("a");
        sampleData.add("b");
        sampleData.add("c");
        sampleData.add("d");
        sampleData.add("e");
        sampleData.add("f");

        Set<String> strings2Set = new HashSet<>();
        for (int i = 0; i < s2.length(); i++) {
            strings2Set.add(s2.substring(i,i+1));
        }
        int reLen = strings2Set.size();
        // 处理数据
        List<String> dataList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> dataSet = new HashSet<>();
        int minData = 99999999;
        for (int i = 0; i < s1.length(); i++) {
            String substring = s1.substring(i, i + 1);
            if(!sampleData.contains(substring)){
                stringBuilder.append(substring);
                dataSet.add(substring);
            }else if(!dataSet.isEmpty() && dataSet.size() <= s2.length()){
                if(reLen - dataSet.size() < minData){
                    dataList.clear();
                    dataList.add(stringBuilder.toString());
                    minData = reLen - dataSet.size();
                }else if(dataSet.size() == minData){
                    dataList.add(stringBuilder.toString());
                }
                dataSet.clear();
                stringBuilder = new StringBuilder();
            }else{
                stringBuilder = new StringBuilder();
                dataSet.clear();
            }
        }
        if(stringBuilder.length() != 0 && stringBuilder.length() <= s2.length()){
            if(reLen - dataSet.size() < minData){
                dataList.clear();
                dataList.add(stringBuilder.toString());
            }else if(reLen - dataSet.size() == minData){
                dataList.add(stringBuilder.toString());
            }
        }
        if(dataList.isEmpty()){
            System.out.println("Not Found");
        }else{
            Collections.sort(dataList);
            System.out.println(dataList.get(dataList.size()-1));
        }

    }

}
