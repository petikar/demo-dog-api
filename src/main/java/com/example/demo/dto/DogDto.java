package com.example.demo.dto;

import com.example.demo.dto.transfer.DogInfo;
import com.example.demo.dto.transfer.Exist;
import com.example.demo.dto.transfer.FindByName;
import com.example.demo.dto.transfer.New;
import com.example.demo.model.Dog;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Objects;

public class DogDto {

    @Null(groups = {New.class, FindByName.class})
    @NotNull(groups = {Exist.class})
    @JsonView({Exist.class, DogInfo.class})
    private Integer id;

    @NotNull(groups = {New.class, Exist.class, FindByName.class})
    @JsonView({Exist.class, DogInfo.class})
    private String name;

    //+ TODO Сделать тип целочисленным и не примитивным (возраст)
    @NotNull(groups = {New.class, Exist.class})
    @JsonView({Exist.class})
    //высчитывать её?
    private Integer age;

    @NotNull(groups = {New.class, Exist.class})
    @JsonView({Exist.class, DogInfo.class})
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    public DogDto() {
    }

    public DogDto(int id, String name, Integer age, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public DogDto(Dog dog) {
        setName(dog.getName());
        setAge(dog.getAge());
        setId(dog.getId());
        setBirthday(LocalDate.from(dog.getBirthday()));
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogDto dogDto = (DogDto) o;
        return Objects.equals(id, dogDto.id) &&
                Objects.equals(name, dogDto.name) &&
                Objects.equals(age, dogDto.age) &&
                Objects.equals(birthday, dogDto.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, birthday);
    }

    @Override
    public String toString() {
        return "DogDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    //+ TODO Добавить дату рождения с типом LocalDateTime*/
}

