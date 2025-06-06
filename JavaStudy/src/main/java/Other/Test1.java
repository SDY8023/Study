package Other;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 18438
 * @date 2024/8/11 14:19
 * @description
 */
public class Test1 {
    public static void main(String[] args) {
        String filename = "D:\\File\\yang\\testFile\\5Gcontent.txt";
        long fileSizeInBytes = 5 * 1024 * 1024 * 1024L; // 5GB
        int chunkSizeInBytes = 1024 * 1024; // 1MB chunk size
        char[] chunk = new char[chunkSizeInBytes];
        for (int i = 0; i < chunk.length; i++) {
            if(i % 3 == 0){
                chunk[i] = 'a';
            }else if(i % 3 == 1){
                chunk[i] = 'b';
            }else{
                chunk[i] = 'c';
            }
        }

        System.out.println("====开始写数据");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            long written = 0;
            while (written < fileSizeInBytes) {
                long remaining = fileSizeInBytes - written;
                int writeSize = (int) Math.min(chunkSizeInBytes, remaining);
                writer.write(chunk, 0, writeSize);
                writer.flush();
                written += writeSize;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=======数据写入结束");
    }
}
