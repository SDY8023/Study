package basicStudy.ExceptionTest;

/**
 * @ClassName MyExpTest
 * @Description
 * @Author SDY
 * @Date 2023/7/30 9:44
 **/
public class MyException extends Exception{
    static final long serialVersionUID = 13465653435L;
    private int idNumber;

    public MyException(String message, int idNumber) {
        super(message);
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }
}
