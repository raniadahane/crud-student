package fr.efrei.student.domain;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    private Integer id;

    private String name;


    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getLastName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
