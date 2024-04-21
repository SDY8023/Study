package FileStream.StandInputOut;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/4/21 19:42
 **/
public class Test1 {
    public static void main(String[] args) {

        boolean flag = true;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            while (flag){
                String data = bufferedReader.readLine();
                System.out.println(data.toUpperCase());
                if(data.equals("e") || data.equals("exit")){
                    flag = false;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }

    }
}
