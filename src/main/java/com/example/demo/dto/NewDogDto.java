package com.example.demo.dto;

import java.time.LocalDateTime;

//TODO @JsonInclude(JsonInclude.Include.NON_NULL)
public class NewDogDto {

    private String name;

    private Integer age;

    private LocalDateTime birthday;

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
}
