package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL) //не показывать поля null(Integer age, LocalDateTime birthday)
public class DogInfoDto extends DogDto {

    private Enum<Age> ageEnum;

    private LocalDate birthDay;

    public Enum<Age> getAgeEnum() {
        return ageEnum;
    }

    public void setAgeEnum(Integer ageDog) {
        if (ageDog <= 3) {
            ageEnum = Age.YOUNG;
        } else if (ageDog > 10) {
            ageEnum = Age.OLDER;
        } else {
            ageEnum = Age.MIDDLE_AGED;
        }
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
