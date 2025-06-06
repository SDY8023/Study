package FileStream.NodeStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 18438
 * @date 2024/4/6 10:52
 * @description
 */
public class TestRead {
    public static void main(String[] args) {
        FileReader fr = null;
        try{
            fr = new FileReader(new File("D:\\File\\yang\\testFile\\test.txt"));
            char[] buf = new char[1024];
            int len;
            while((len = fr.read(buf)) != -1){
                System.out.println(new String(buf,0,len));
            }
        }catch (IOException e){
            System.out.println("read-exception:"+e.getMessage());
        }finally {
            if(fr != null){
                try{
                    fr.close();
                }catch (IOException e){
                    System.out.println("colse-exception:"+e.getMessage());
                }
            }
        }

    }
}
