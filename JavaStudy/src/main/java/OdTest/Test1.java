package OdTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/4/24 20:22
 **/
public class Test1 {
    public static void main(String[] args) {
        test2();
    }

    /**
     * 计算勾股数
     */
    public void test1(int n,int m){


        if( m <= n){
            System.out.println("Na");
            return;
        }
        List<String> result = new ArrayList<>();
        for(int a = n; a <= m-2; a++){
            for(int b = a+1; b <= m-1; b++){
                for(int c = b + 1; c <= m; c++){
                    if((Math.pow(c,2) == Math.pow(a,2)+ Math.pow(b,2)) && checkZhiShu(a,b) && checkZhiShu(b,c) && checkZhiShu(a,c)){
                        result.add(a+" " + b + " " + c);
                    }
                }
            }
        }
        if(result.isEmpty()){
            System.out.println("Na");
        }else{
            result.forEach(System.out::println);
        }

    }

    public void test11(int n,int m){
        List<String> result = new ArrayList<>();
        for(int a = n; a <=m ;a++){
            for(int b = a+1;b <= m;b++){
                int c = (int)Math.sqrt(a * a + b * b);
                if(c > m){
                    break;
                }
                if(c * c == a * a + b * b){
                    if(checkZhiShu(a,b) && checkZhiShu(a,c) && checkZhiShu(b,c)){
                        result.add(a+" "+b+" "+c);
                    }
                }
            }
        }
        if(result.isEmpty()){
            System.out.println("Na");
        }else{
            result.forEach(System.out::println);
        }
    }

    public static void test2(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String[] array1 = scanner.nextLine().split(" ");
            String[] array2 = scanner.nextLine().split(" ");
            int k = scanner.nextInt();
            int len1 = Integer.parseInt(array1[0]);
            int len2 = Integer.parseInt(array2[0]);
            List<Integer> result = new ArrayList<>();
            for(int i = 1; i < len1; i++){
                int a = Integer.parseInt(array1[i]);
                for(int j = 1; j < len2; j++){
                    int b = Integer.parseInt(array2[j]);
                    result.add(a+b);
                }
            }
            Collections.sort(result);
            int total = 0;
            for(int i = 0; i < k; i++){
                total += result.get(i);
            }
            System.out.println(total);

        }
    }

    /**
     * 判断互质
     * @param a
     * @param b
     * @return
     */
    public boolean checkZhiShu(int a,int b){
        if(a <= 0 || b <= 0){
            return false;
        }
        while (b != 0){
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a == 1;

    }
}
