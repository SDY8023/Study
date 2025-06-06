package FileStream.ObjectStream;

import bean.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName ObjectStreamTest
 * @Description
 * @Author SDY
 * @Date 2024/4/21 20:30
 **/
public class ObjectStreamTest {
    public static void main(String[] args) {
        ObjectStreamTest objectStreamTest = new ObjectStreamTest();
        objectStreamTest.testObjectStreamOutput();
        objectStreamTest.testObjectStreamInput();

    }

    /**
     * 测试输出流
     */
    public void testObjectStreamOutput(){
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("F:\\Test\\dir1\\destObjectData.txt"));
            Person p = new Person("韩梅梅", 18, "中华大街");
            oos.writeObject(p);
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(oos != null){
                    oos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 测试输入流
     */
    public void testObjectStreamInput(){
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream("F:\\Test\\dir1\\destObjectData.txt"));
            Person p = (Person)ois.readObject();
            System.out.println(p.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(ois != null){
                    ois.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
