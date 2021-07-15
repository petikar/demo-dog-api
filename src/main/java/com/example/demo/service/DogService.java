package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.model.Dog;

import java.util.List;

public interface DogService {

    Dog save(DogDto newObject);

    List<DogDto> findAll();

    DogDto findById(int id);
}
