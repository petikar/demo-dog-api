package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Dog implements Serializable {

    //Dummy
    @Id
    @GeneratedValue()
    private int id;
    private int abc;

    private String name;

    //+TODO Сделать тип целочисленным и не примитивным
    //высчитывать её?
    private Integer age;

    //+TODO Добавить дату рождения с типом DateTime
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime birthday;

    public Dog() {
    }

    public Dog(String name, Integer age, LocalDateTime birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return id == dog.id &&
                Objects.equals(name, dog.name) &&
                Objects.equals(age, dog.age) &&
                Objects.equals(birthday, dog.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, birthday);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}