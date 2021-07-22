package com.example.demo.dto;

import java.time.LocalDate;

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
