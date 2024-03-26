package basicStudy.FileStudy;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/3/26 21:55
 **/
public class Test1 {
    public static void main(String[] args) throws IOException {
        Test1 test1 = new Test1();
        test1.deleteAll();

    }

    public void test1() throws IOException {
        File dir1 = new File("F:/Test/");
        if(!dir1.exists()){
            dir1.mkdir();
        }
        File dir2 = new File(dir1,"dir2");
        if(!dir2.exists()){
            dir2.mkdirs();
        }
        File dir4 = new File(dir1,"dir3/dir4");
        if(!dir4.exists()){
            dir4.mkdirs();
        }
        File file1 = new File(dir1,"test1.txt");
        if(!file1.exists()){
            file1.createNewFile();
        }
    }

    public void testDelete(){
        File file = new File("F:/Test/");
        boolean delete = file.delete();
        System.out.println(delete);
    }

    public void checkFileName(){
        File file = new File("F:/Test/");
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isFile() && file1.getName().endsWith(".txt")){
                System.out.println(file1.getName());
            }
        }
    }

    public void deleteAll(){
        File file = new File("F:/Test/");
        File[] files = file.listFiles();
        System.out.println(files.length);
        for (File file1 : files) {

            boolean delete = file1.delete();
            System.out.println(delete);
        }
    }
}
