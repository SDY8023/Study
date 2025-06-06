package OdTest;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test6
 * @Description
 * @Author SDY
 * @Date 2024/5/12 20:05
 **/
public class Test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int numRows = sc.nextInt();
        System.out.println(ZConvert(s,numRows));

    }

    /**
     * 最长回文字符串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s){
        List<String> result = new ArrayList<>();
        String resultData = "";
        for (int i = 0; i < s.length(); i++) {
            StringBuffer stringBuffer = new StringBuffer();
            for(int j = i; j < s.length(); j++){
                stringBuffer.append(s.substring(j, j+ 1));
                if(stringBuffer.toString().equals(stringBuffer.reverse().toString())){
                    // 再反转回来
                    stringBuffer.reverse();
                    result.add(stringBuffer.toString());
                    if(resultData.length() < stringBuffer.length()){
                        resultData =  stringBuffer.toString();
                    }
                }else{
                    stringBuffer.reverse();
                }
            }
            if(resultData.length() >= s.substring(i).length()){
                break;
            }
        }
        System.out.println(resultData);
        return resultData;
    }

    public static String longestPalindrome2(String s){
        String ans = "";
        int max = 0;
        int len  = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j <= len; j++) {
                String test = s.substring(i, j);
                if(isPalindromic(test) && test.length() > max){
                    ans = test;
                    max = test.length();
                }
            }
            if(s.substring(i).length() <= max){
                break;
            }
        }
        return ans;
    }

    /**
     * 判度是否为回文字符串
     * 从第一位判断到中间 len/2
     * @param s
     * @return
     */
    public static boolean isPalindromic(String s){
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if(s.charAt(i) != s.charAt(len - i - 1)){
                return false;
            }
        }
        return true;
    }

    /**
     * Z字形变换
     * @param s
     * @return
     */
    public static String ZConvert(String s,int numRows){
        int len = s.length();
        if(len == 1 || numRows == 1 || len < numRows){
            return s;
        }
        // 之间字符串数量
        int n = numRows - 2;
        List<String> dataList = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < s.length()){
            if(j % 2 == 0){
                if(i+numRows < s.length()){
                    dataList.add(s.substring(i,i+numRows));
                }else{
                    String tmpData = s.substring(i);
                    String suffix = "";
                    for(int d=0;d < numRows-tmpData.length();d++){
                        suffix += " ";
                    }
                    dataList.add(tmpData+suffix);
                }
                i += numRows;
            }else{
                String d = "";
                if(i+n < len){
                    d = s.substring(i,i+n);
                }else{
                    d = s.substring(i);
                }
                StringBuilder d1 = new StringBuilder();
                for (int l = 0; l < d.length(); l++) {
                    d1.append(d.substring(l,l+1));
                }
                String preData = "";
                for (int k = 0; k < numRows - d1.length()-1; k++) {
                    preData += " ";
                }
                dataList.add(preData + d1.reverse() + " ");
                i += n;
            }
            j += 1;
        }
        System.out.println(dataList);
        StringBuffer result = new StringBuffer();
        for(int m=0;m < numRows; m++){
            for (int i1 = 0; i1 < dataList.size(); i1++) {
//                System.out.println(dataList.get(i1));
//                System.out.println(dataList.get(i1).length());
                String substring = dataList.get(i1).substring(m, m + 1);
                if(!substring.equals(" ")){
                    result.append(dataList.get(i1).charAt(m));
                }
            }
        }
        return result.toString();

    }

}
