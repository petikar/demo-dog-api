package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL) //не показывать поля null(Integer age, LocalDateTime birthday)
public class DogInfoDto extends DogDto {

    @JsonProperty("age")
    private Age ageEnum;

    @JsonProperty("birthday")
    private LocalDate birthdayLocalDate;

    public Age getAgeEnum() {
        return ageEnum;
    }

    public void setAgeEnum(Age ageEnum) {
        this.ageEnum = ageEnum;
    }

    public LocalDate getBirthdayLocalDate() {
        return birthdayLocalDate;
    }

    public void setBirthdayLocalDate(LocalDate birthdayLocalDate) {
        this.birthdayLocalDate = birthdayLocalDate;
    }
}
