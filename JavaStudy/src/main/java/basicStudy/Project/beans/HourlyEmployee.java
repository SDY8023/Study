package basicStudy.Project.beans;

/**
 * @ClassName HourlyEmployee
 * @Description
 * @Author SDY
 * @Date 2023/7/19 21:06
 **/
public class HourlyEmployee extends Employee{
    private int wage;
    private int hour;

    public HourlyEmployee(String name, String number, MyDate birthday, int wage, int hour) {
        super(name, number, birthday);
        this.wage = wage;
        this.hour = hour;
    }

    public HourlyEmployee(int wage, int hour) {
        this.wage = wage;
        this.hour = hour;
    }

    public HourlyEmployee() {
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String earnings() {
        return String.valueOf(wage * hour);
    }
}
