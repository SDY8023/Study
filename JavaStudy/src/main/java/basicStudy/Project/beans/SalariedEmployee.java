package basicStudy.Project.beans;

/**
 * @ClassName SalariedEmployee
 * @Description
 * @Author SDY
 * @Date 2023/7/19 21:00
 **/
public class SalariedEmployee extends Employee{
    private String monthlySalary;

    public SalariedEmployee(String name, String number, MyDate birthday, String monthlySalary) {
        super(name, number, birthday);
        this.monthlySalary = monthlySalary;
    }

    public SalariedEmployee(String monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public SalariedEmployee() {
    }

    @Override
    public String earnings() {
        return monthlySalary;
    }

}
