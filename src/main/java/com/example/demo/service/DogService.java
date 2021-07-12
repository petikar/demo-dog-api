package com.example.demo.service;

import com.example.demo.model.Dog;

import java.util.List;

public interface DogService {

    void save(Dog newObject);

    List<Dog> findAll();

    Dog findById(int id);
}
