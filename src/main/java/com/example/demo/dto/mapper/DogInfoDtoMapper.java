package com.example.demo.dto.mapper;

import com.example.demo.dto.DogInfoDto;
import com.example.demo.model.Dog;

public interface DogInfoDtoMapper {
    Dog toEntity(DogInfoDto dogInfoDto);

    DogInfoDto toDogInfoDto(Dog dog);
}
