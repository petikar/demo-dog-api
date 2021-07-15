package com.example.demo.dto;

import com.example.demo.utils.ApplicationParameters;

public class DogDtoWithComment {

    private int id;

    private String name;

    private double age;

    private String comment;

    public DogDtoWithComment(DogDto dogDto, String comment) {
        this.id = dogDto.getId();
        this.name = dogDto.getName();
        this.age = dogDto.getAge();
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
