package OdTest;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName Test4
 * @Description
 * @Author SDY
 * @Date 2024/4/27 19:36
 **/
public class Test4 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring3("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s){
        int maxLen = 0;
        for(int i=0; i < s.length();i++){
            List<String> sub = new ArrayList<>();
            sub.add(s.substring(i,i+1));
            for(int j = i + 1;j < s.length();j++){
                if(sub.contains(s.substring(j,j+1))){
                    break;
                }else{
                    sub.add(s.substring(j,j+1));
                }
            }
            maxLen = Math.max(maxLen, sub.size());
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring2(String s){
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        while ( i < s.length()){
            int pos = s.indexOf(s.charAt(i),flag);
            if(pos < i){
                if(length > result){
                    result = length;
                }
                if(result >= s.length() - pos - 1){
                    return result;
                }
                length = i - pos -1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return length;
    }

    public static int lengthOfLongestSubstring3(String s){
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        while (i < s.length()){
            // 从flag位置计算当前i所在位置的字符所在位置，就是寻找前边字串中是否有重复字符
            int pos = s.indexOf(s.charAt(i), flag);
            // 若没有重复的，pos和i是相等的
            if(pos < i){
                // 找到了重复字符串
                result = Math.max(length, result);
                if(result >= s.length() - pos - 1){
                    // 若计算的长度已经大于等于字符串剩余字符串，则直接返回即可
                    return result;
                }
                // flag位置要后移，当前length长度也需要调整
                length = i - pos - 1;
                // flag定位位置后移
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return result;
    }
}
