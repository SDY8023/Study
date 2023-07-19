package basicStudy.Project.beans;

/**
 * @ClassName MyDate
 * @Description
 * @Author SDY
 * @Date 2023/7/19 20:57
 **/
public class MyDate {
    private String year;
    private String month;
    private String day;

    public MyDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate() {
    }

    public String toDateString(){
        return year+"年"+month+"月"+day+"日";
    }
}
