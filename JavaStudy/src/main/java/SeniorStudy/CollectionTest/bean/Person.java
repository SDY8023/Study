package SeniorStudy.CollectionTest.bean;

import java.util.Objects;

/**
 * @ClassName Person
 * @Description
 * @Author SDY
 * @Date 2023/8/28 20:48
 **/
public class Person {
    private int id;

    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return id == person.id && this.name.equalsIgnoreCase(person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
