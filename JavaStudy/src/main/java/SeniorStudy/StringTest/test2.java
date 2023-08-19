package SeniorStudy.StringTest;

import com.sun.xml.internal.ws.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @ClassName test2
 * @Description
 * @Author SDY
 * @Date 2023/8/13 9:41
 **/
public class test2 {
    public static void main(String[] args) {
        test2 test2 = new test2();
        test2.test5();

    }

    public void test1(){
        String str = "中";
        try{
            System.out.println(str.getBytes("ISO8859-1").length);
            System.out.println(str.getBytes("GBK").length);
            System.out.println(str.getBytes(StandardCharsets.UTF_8).length);

            System.out.println(new String(str.getBytes("ISO8859-1"),"ISO8859-1"));//表示不了中文
            System.out.println(new String(str.getBytes("GBK"),"GBK"));
            System.out.println(new String(str.getBytes(StandardCharsets.UTF_8),StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void test3(){
        String str1  = " akjsdfhka asdfsa ";
        String str2 = "abcdefg";
        // 去除字符串两端的空格
        System.out.println(str1.trim());
        // 反转字符串
        System.out.println(reverse(str2,2,5));

        System.out.println(getCount("ab","abkkcadkabkebfkabkskab"));
    }

    public void test4(){
        String str = null;
        StringBuffer sb = new StringBuffer();// 默认长度是4
        sb.append(str);
        System.out.println(sb.length());// 4
        System.out.println(sb);// null

        StringBuffer sb1 = new StringBuffer(str);
        System.out.println(sb1); // null
    }

    public void test5(){
        Date date = new Date();
        System.out.println(date);

        System.out.println(System.currentTimeMillis());
        System.out.println(date.getTime());

        Date date1 = new Date(date.getTime());
        System.out.println(date1.getTime());
        System.out.println(date1.toString());
    }

    /**
     * 字符串反转
     * @param str
     * @param start
     * @param end
     * @return
     */
    public String reverse(String str,int start,int end){
        String substring = str.substring(start,end+1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = substring.length()-1; i >= 0; i--) {
            stringBuilder.append(substring.substring(i,i+1));
        }
        return str.replace(substring, stringBuilder.toString());
    }

    /**
     * 获取一个字符串在另一个字符串中出现的次数
     * @param str1
     * @param str2
     * @return
     */
    public int getCount(String str1,String str2){
        int length1 = str1.length();
        int count = 0;
        for (int i = 0; i < str2.length(); i = i + length1) {
            if(i + length1 <= str2.length()){
                String substring = str2.substring(i, i + length1);
                if(substring.equalsIgnoreCase(str1)){
                    count++;
                }
            }else{
                break;
            }

        }

        return count;
    }
}
