package basicStudy.GenericityTest.Test1;

import basicStudy.GenericityTest.Genericity.Employee;
import basicStudy.GenericityTest.Genericity.MyDate;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName Test2
 * @Description
 * @Author SDY
 * @Date 2024/3/14 22:34
 **/
public class Test2 {
    public static void main(String[] args) {
        Employee employee1 = new Employee("e1",20,new MyDate(4,21,1995));
        Employee employee2 = new Employee("e2",21,new MyDate(5,1,1994));
        Employee employee3 = new Employee("e3",22,new MyDate(3,2,1995));
        Employee employee4 = new Employee("e4",23,new MyDate(7,27,1997));
        Employee employee5 = new Employee("e5",24,new MyDate(9,23,1999));

        // 在集合不传入comparator函数时，使用对象中实现的comparator接口，在集合实现后使用集合实现的函数
        Set<Employee> employees = new TreeSet<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);

        for (Employee employee : employees) {
            System.out.println(employee);
        }


    }
}
