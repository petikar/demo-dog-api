package com.example.demo.service;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository repository;

    @Override
    public void save(Dog newDog) {
        repository.save(newDog);
    }

    @Override
    public List<Dog> findAll() {
        return repository.findAll();
    }

    @Override
    public Dog findById(int id) {
        return repository.findById(id).get();
    }
}
