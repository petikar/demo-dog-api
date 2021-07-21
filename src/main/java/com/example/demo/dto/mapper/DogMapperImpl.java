package com.example.demo.dto.mapper;

import com.example.demo.dto.DogDto;
import com.example.demo.model.Dog;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DogMapperImpl implements DogMapper {

    @Override
    public Dog toEntity(DogDto dogDto) {
        Dog dog = new Dog();
        //dog.setId(dogDto.getId());
        dog.setName(dogDto.getName());
        dog.setAge(dogDto.getAge());
        LocalTime time = LocalTime.of(0, 0);
        LocalDateTime birthday = LocalDateTime.of(dogDto.getBirthday(), time);
        dog.setBirthday(birthday);
        return dog;
    }

    @Override
    public DogDto toDto(Dog dog) {
        DogDto dogDto = new DogDto();
        dogDto.setId(dog.getId());
        dogDto.setName(dog.getName());
        dogDto.setAge(dog.getAge());
        LocalDateTime birthdayDog = dog.getBirthday();
        int day = birthdayDog.getDayOfMonth();
        int month = birthdayDog.getMonthValue();
        int year = birthdayDog.getYear();
        LocalDate birthday = LocalDate.of(year, month, day);
        dogDto.setBirthday(birthday);
        //dogInfoDto.setBirthday(LocalDate.from(dog.getBirthday()));
        return dogDto;
    }
}
