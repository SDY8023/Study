package basicStudy.Project.beans;

import java.util.Date;

/**
 * @ClassName PayrollSystem
 * @Description
 * @Author SDY
 * @Date 2023/7/19 21:09
 **/
public class PayrollSystem {
    public static void main(String[] args) {
        Employee employees[] = new Employee[10];
        employees[0] = new SalariedEmployee("zs","0001",new MyDate("2023","7","19"),"3000");
        employees[1] = new HourlyEmployee("ls","0002",new MyDate("1995","9","1"),50,50);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
