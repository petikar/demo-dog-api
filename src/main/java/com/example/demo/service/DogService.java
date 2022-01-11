package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;

import java.util.List;

public interface DogService {

    DogDto save(DogDto newObject);

    List<DogDto> findAll();

    DogDtoWithComment findDogDtoWithCommentById(int id) throws Exception;

    List<DogInfoDto> findByName(String name);

}
