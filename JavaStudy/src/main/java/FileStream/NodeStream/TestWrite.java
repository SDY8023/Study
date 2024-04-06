package FileStream.NodeStream;

import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 18438
 * @date 2024/4/6 11:01
 * @description
 */
public class TestWrite {
    public static void main(String[] args) {
        FileWriter fw = null;
        try{
            fw = new FileWriter("D:\\File\\yang\\testFile\\testWrite.txt");
            String content = "sdy-ksdjkdf-孙东阳";
            fw.write(content);
        }catch (IOException ioe){
            System.out.println("write-exception:"+ioe.getMessage());
        }finally {
            if(fw != null){
                try{
                    fw.close();
                }catch (IOException ioe){
                    System.out.println("close-exception:"+ioe.getMessage());
                }
            }
        }

    }
}
