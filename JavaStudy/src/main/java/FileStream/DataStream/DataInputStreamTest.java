package FileStream.DataStream;

import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * @ClassName DataInputStreamTest
 * @Description
 * @Author SDY
 * @Date 2024/4/21 20:09
 **/
public class DataInputStreamTest {
    public static void main(String[] args) {
        DataInputStream dis = null;
        try{
            dis = new DataInputStream(new FileInputStream("F:\\Test\\dir1\\destData.dat"));
            String info = dis.readUTF();
            boolean flag = dis.readBoolean();
            long time = dis.readLong();
            System.out.println(info);
            System.out.println(flag);
            System.out.println(time);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(dis != null){
                    dis.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
