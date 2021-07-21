package com.example.demo.dto.mapper;

import com.example.demo.dto.DogDto;
import com.example.demo.model.Dog;

public interface DogMapper {
    Dog toEntity(DogDto dogDto);

    DogDto toDto(Dog dog);
}
