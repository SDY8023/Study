package CreatData;

import java.io.FileWriter;
import java.util.Random;

/**
 * @ClassName Create1
 * @Description
 * @Author SDY
 * @Date 2023/9/16 9:36
 **/
public class Create1 {
    int queueId = 1;
    public static void main(String[] args) {
        new Create1().createHiveData(100000);

    }

    /**
     * 构造hive数据
     */
    public void createHiveData(int num){
        int id = 1;
        try{
            FileWriter fileWriter = new FileWriter("F:\\Study\\creatData\\create1.txt");
            while (id <= num){
                String s = concatString(id);
                System.out.println(s);
                fileWriter.write(s);
                id++;
            }
            fileWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 拼接字符串
     */
    public String concatString(int id){
        StringBuffer stringBuffer = new StringBuffer();
        int idNum = id;
        Random random = new Random(10);
        String testGuid = "test_guid"+id;
        String testTxt = "test_txt"+id;
        String testNum = "test_num"+id;
        String testType = "PTR";
        int siteNum = id % 8 == 0 ? 8 : id % 8;
        queueId = id / 8 == 0 ? id / 8 : queueId;
        int partId = id;
        String pinIndex = "" ;
        double pinValue = random.nextDouble();
        String testIndex = "test_index"+id;
        double testValue = random.nextDouble();
        double transTestValue = testValue;
        String testResult = id % 2 == 0 ? "P" : "F";
        long createTime = System.currentTimeMillis();
        String testSuite = "test_suite"+id;
        String vectorName = "vector_name"+id;
        String vectorPattern = "vector_pattern"+id;
        stringBuffer.append(id)
                .append("|")
                .append(testGuid)
                .append("|")
                .append(testTxt)
                .append("|")
                .append(testNum)
                .append("|")
                .append(testType)
                .append("|")
                .append(siteNum)
                .append("|")
                .append(queueId)
                .append("|")
                .append(partId)
                .append("|")
                .append(pinIndex)
                .append("|")
                .append(pinValue)
                .append("|")
                .append(testIndex)
                .append("|")
                .append(testValue)
                .append("|")
                .append(transTestValue)
                .append("|")
                .append(testResult)
                .append("|")
                .append(createTime)
                .append("|")
                .append(testSuite)
                .append("|")
                .append(vectorName)
                .append("|")
                .append(vectorPattern)
                .append("\n");
        return stringBuffer.toString();
    }
}
