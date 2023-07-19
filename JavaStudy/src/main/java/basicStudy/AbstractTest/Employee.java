package basicStudy.AbstractTest;

/**
 * @ClassName Employee
 * @Description
 * @Author SDY
 * @Date 2023/7/19 20:25
 **/
abstract class Employee {
    private String name;
    private String id;
    private String salary;

    public Employee(String name, String id, String salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public abstract String work();
}
