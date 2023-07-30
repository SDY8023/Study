package basicStudy.ExceptionTest;

/**
 * @ClassName MyExpTest
 * @Description
 * @Author SDY
 * @Date 2023/7/30 9:46
 **/
public class MyExpTest {
    public void regist(int num) throws MyException{
        if(num < 0)
            throw new MyException("人数为负值",3);
        else
            System.out.println("登记人数"+num);
    }
    public void manager(){
        try{
            regist(-5);
        }catch (MyException e){
            System.out.println("登记失败，出错种类"+e.getIdNumber());
        }
        System.out.println("本次登记操作结束");
    }

    public static void main(String[] args) {
        MyExpTest myExpTest = new MyExpTest();
        myExpTest.manager();
    }
}
