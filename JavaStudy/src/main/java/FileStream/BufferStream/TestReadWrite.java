package FileStream.BufferStream;

import java.io.*;

/**
 * @author 18438
 * @date 2024/4/6 11:55
 * @description
 */
public class TestReadWrite {
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            br = new BufferedReader(new FileReader("D:\\File\\yang\\testFile\\test.txt"));
            bw = new BufferedWriter(new FileWriter("D:\\File\\yang\\testFile\\test2.txt"));
            String str;
            while ((str = br.readLine()) != null){
                bw.write(str);
                bw.newLine(); // 写入分隔符
            }
            bw.flush(); // 刷写进磁盘
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            try{
                if(bw != null){
                    bw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                if(br != null){
                    br.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
