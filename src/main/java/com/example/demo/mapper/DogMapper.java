package com.example.demo.mapper;

import com.example.demo.dto.Age;
import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;
import com.example.demo.model.Dog;
import com.example.demo.utils.ConfigProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class DogMapper {

    @Autowired
    private ConfigProperties configProperties;

    public ConfigProperties getConfigProperties() {
        return configProperties;
    }

    //DogDtoWithComment
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //qualifiedByName - решение Данила, используется "левый" источник
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Mapping(source = "name", target = "comment", qualifiedByName = "setComment")
    public abstract DogDtoWithComment dogToDogDtoWithComment(Dog dog);


/*    //qualifiedByName требует отображение чего-то на что-то
    @Mapping(target = "comment", qualifiedByName = "setComment")
    //Error:(39, 5) java: The type of parameter "dog" has no property named
    // "comment". Please define the source property explicitly.
    public abstract DogDtoWithComment dogToDogDtoWithComment(Dog dog);*/

    @Named("setComment")
    public String setComment(String name) {
        return getConfigProperties().getComment();
    }

    //DogDto
    public abstract DogDto dogToDogDto(Dog dog);

    //DogDto without id
    @Mapping(target = "id", ignore = true)
    public abstract Dog newDogDtoToDog(DogDto newDogDto);

    //DogInfoDto
    //название полей сделала через @JsonProperty в самом классе DogInfoDto
    @Mapping(target = "ageEnum", source = "age", qualifiedByName = "ageMapEnumAge")
    @Mapping(target = "birthdayLocalDate", source = "birthday", qualifiedByName = "birthdayMapBirthdayLocalDate")
    public abstract DogInfoDto dogToDogInfoDto(Dog dog);

    @Named("ageMapEnumAge")
    Age map(Integer ageDog){
        if (ageDog <= 3) {
            return Age.YOUNG;
        } else if (ageDog > 10) {
            return Age.OLDER;
        } else {
            return Age.MIDDLE_AGED;
        }
    }

   @Named("birthdayMapBirthdayLocalDate")
   LocalDate map(LocalDateTime birthday){
        return birthday.toLocalDate();
   }
}

