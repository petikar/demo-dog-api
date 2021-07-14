package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

//TODO Должен имплементировать Serializable
@Entity
public class Dog {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private double age;

//    @ManyToOne
//    private Person owner;

    public Dog() {
    }

    public Dog(String name, double age) {
        this.name = name;
        this.age = age;
     //   this.owner = owner;
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

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Double.compare(dog.age, age) == 0 &&
                Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
