package sample.servletcomponents.sampleservletcomponents.Models;

import java.util.Date;

public class Person {
    private String name;
    private int age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
