package FileStream.practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/4/15 20:56
 **/
public class Test1 {
    public static void main(String[] args) {
        new Test1().practice3();
    }

    /**
     * 练习FileInPutStream
     */
    public void practice1(){
        try{
            FileInputStream fileInputStream = new FileInputStream("F:\\Test\\dir1\\尚硅谷_宋红康_第13章_IO流.pdf");
            FileOutputStream fileOutputStream = new FileOutputStream("F:\\Test\\dir2\\test1.pdf");
            byte[] inputData = new byte[2277473];
            System.out.println("开始时间:"+System.currentTimeMillis());
            while (fileInputStream.read(inputData) != -1){
                fileOutputStream.write(inputData);
            }
            System.out.println("结束时间:"+System.currentTimeMillis());
            fileInputStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 使用缓冲流
     */
    public void practice2(){
        try{
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("F:\\Test\\dir1\\尚硅谷_宋红康_第13章_IO流.pdf"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:\\Test\\dir2\\test1.pdf"));
            byte[] bytes = new byte[1];
            System.out.println("开始时间:"+System.currentTimeMillis());
            while (bufferedInputStream.read(bytes) != -1){
                bufferedOutputStream.write(bytes);
            }
            System.out.println("结束时间:"+System.currentTimeMillis());
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void practice3(){
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        HashMap<String, Integer> result = new HashMap<>();
        try{
            bufferedInputStream = new BufferedInputStream(new FileInputStream("F:\\Test\\dir2\\test1.txt"));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:\\Test\\dir2\\test2.txt"));
            byte[] bytes = new byte[1024];
            int len = 0;
            while (len != -1){
                len = bufferedInputStream.read(bytes);
                String content = new String(bytes, "UTF-8");
                String[] s = content.split(" ");
                for (String s1 : s) {
                    result.put(s1,result.getOrDefault(s1,0) + 1);
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (String k : result.keySet()) {
                stringBuffer.append(k+":"+result.get(k) + "\n");
            }
            byte[] bytes1 = stringBuffer.toString().getBytes(StandardCharsets.UTF_8);
            bufferedOutputStream.write(bytes1);
            bufferedOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bufferedInputStream != null){
                    bufferedInputStream.close();
                }
                if(bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
