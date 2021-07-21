package com.example.demo.dto;

import com.example.demo.dto.transfer.DogInfo;
import com.example.demo.model.Dog;
import com.fasterxml.jackson.annotation.JsonView;

public class DogInfoDto extends DogDto {

    @JsonView({DogInfo.class})
    private Enum<Age> ageEnum;

    public DogInfoDto() {
    }

    public DogInfoDto(Dog dog) {
        super(dog);
        setAgeEnum(dog.getAge());
    }

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
}
