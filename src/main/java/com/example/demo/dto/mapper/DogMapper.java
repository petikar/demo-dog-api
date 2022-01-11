package com.example.demo.dto.mapper;

import com.example.demo.dto.*;
import com.example.demo.model.Dog;
import com.example.demo.utils.ConfigProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DogMapper {

    @Autowired
    private ConfigProperties configProperties;

    public ConfigProperties getConfigProperties() {
        return configProperties;
    }

    //DogDtoWithComment
    @Mapping(target = "comment", expression = "java(getConfigProperties().getComment())")
    public abstract DogDtoWithComment dogToDogDtoWithComment(Dog dog);
    public abstract Dog dogDtoWithCommentToDog(DogDtoWithComment dogDtoWithComment);

    //DogDto
    public abstract Dog dogDtoToDog(DogDto dogDto);
    public abstract DogDto dogToDogDto(Dog dog);

    //NewDogDto without id
    public abstract Dog newDogDtoToDog(NewDogDto newdogDto);

    //DogInfoDto
    public DogInfoDto dogToDogInfoDto(Dog dog) {

        if (dog == null) {
            return null;
        }

        DogInfoDto dogInfoDto = new DogInfoDto();

        dogInfoDto.setId(dog.getId());
        dogInfoDto.setName(dog.getName());
        dogInfoDto.setBirthDay(dog.getBirthday().toLocalDate());
        dogInfoDto.setAgeEnum(dog.getAge());

        return dogInfoDto;
    }

}

