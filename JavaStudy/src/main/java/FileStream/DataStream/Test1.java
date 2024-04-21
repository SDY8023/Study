package FileStream.DataStream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2024/4/21 20:04
 **/
public class Test1 {
    public static void main(String[] args) {
        DataOutputStream dos = null;
        try{
            dos = new DataOutputStream(new FileOutputStream("F:\\Test\\dir1\\destData.dat"));
            dos.writeUTF("我爱北京");
            dos.writeBoolean(false);
            dos.writeLong(12344325L);
            System.out.println("写入成功");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(dos != null){
                    dos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
