package com.example.demo.dto;

public class DogDto {

    private int id;

    private String name;

    private double age;

    public DogDto(int id, String name, double age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public DogDto() {
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


}

