package com.example.demo.dto.mapper;

import com.example.demo.dto.DogInfoDto;
import com.example.demo.model.Dog;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DogInfoDtoMapperImpl implements DogInfoDtoMapper {

    //сильно зависит от новых полей

    @Override
    public Dog toEntity(DogInfoDto dogInfoDto) {
        Dog dog = new Dog();
        dog.setId(dogInfoDto.getId());
        dog.setName(dogInfoDto.getName());
        dog.setAge(dogInfoDto.getAge());
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime birthday = LocalDateTime.of(dogInfoDto.getBirthday(), time);
        dog.setBirthday(birthday);
        return dog;
    }

    @Override
    public DogInfoDto toDogInfoDto(Dog dog) {
        DogInfoDto dogInfoDto = new DogInfoDto();
        dogInfoDto.setId(dog.getId());
        dogInfoDto.setName(dog.getName());
        dogInfoDto.setAgeEnum(dog.getAge());
        LocalDateTime birthdayDog = dog.getBirthday();
        int day = birthdayDog.getDayOfMonth();
        int month = birthdayDog.getMonthValue();
        int year = birthdayDog.getYear();

        LocalDate birthday = LocalDate.of(year, month, day);
        dogInfoDto.setBirthday(birthday);
        //dogInfoDto.setBirthday(LocalDate.from(dog.getBirthday()));
        return dogInfoDto;
    }
}
