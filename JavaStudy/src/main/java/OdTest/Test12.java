package OdTest;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName Test12
 * @Description
 * @Author SDY
 * @Date 2024/6/11 21:09
 **/
public class Test12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        intToRoman(Integer.parseInt(sc.next()));

//        System.out.println(Math.pow(5,2));

    }

    public static String intToRoman(int num) {
        int x = 0;
        ArrayList<String> result = new ArrayList<>();
        while (num != 0){
            int temp = num % 10;
            num /= 10;
            StringBuilder t = new StringBuilder();
            if(temp == 4){
                if(x == 0){
                    result.add("IV");
                }else if(x == 1){
                    result.add("XL");
                }else if(x == 2){
                    result.add("CD");
                }
            }else if(temp == 9){
                if(x == 0){
                    result.add("IX");
                }else if(x == 1){
                    result.add("XC");
                }else if(x == 2){
                    result.add("CM");
                }
            }else{
                if(x == 0){
                    while (temp >= 5){
                        t.append("V");
                        temp -= 5;
                    }
                    for (int i = 0; i < temp; i++) {
                        t.append("I");
                    }
                }else if(x == 1){
                    while (temp >= 5){
                        t.append("L");
                        temp -= 5;
                    }
                    for (int i = 0; i < temp; i++) {
                        t.append("X");
                    }
                }else if(x == 2){
                    while (temp >= 5){
                        t.append("D");
                        temp -= 5;
                    }
                    for (int i = 0; i < temp; i++) {
                        t.append("C");
                    }
                }else if(x == 3){
                    for (int i = 0; i < temp; i++) {
                        t.append("M");
                    }
                }
                result.add(t.toString());
            }
            x++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = result.size()-1; i >= 0; i--) {
            stringBuilder.append(result.get(i));
        }
        System.out.println(stringBuilder);

        return stringBuilder.toString();
    }

}
