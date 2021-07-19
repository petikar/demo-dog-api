package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DogServiceImpl implements DogService {

    private final DogRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public DogServiceImpl(DogRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Dog save(DogDto newDog) {
        //TODO Сервис не должен возвращать Entity
        Dog dog = modelMapper.map(newDog, Dog.class);
        return repository.save(dog);
    }

    @Override
    public List<DogDto> findAll() {
        List<Dog> dogs = repository.findAll();
        //TODO А если, вдруг, классы будут не полностью совместимы, см. findByName?
        List<DogDto> dogsDto = dogs.stream().map(dog -> modelMapper.map(dog, DogDto.class)).collect(Collectors.toList());

        return dogsDto;
    }

    @Override
    public DogDto findById(int id) {
        //найти как настроить валидацию аннотациями
        if(id<1){
            //TODO Это абсолютно разные типы ошибок
            throw new ApiRequestException("id must be an integer greater than 1, id = "+id+" doesn't exist");
        }
        //TODO Это абсолютно разные типы ошибок
        return modelMapper.map(repository.findById(id).orElseThrow(() -> new ApiRequestException("ApiRequestException: Dog doesn't found by id=" + id)), DogDto.class);
    }

    //TODO findByName
    // Должен возвращать новый DTO DogInfoDto:
    // вместо age должен быть Enum: Young (age <= 3), Middle-Aged (age <= 10), Older
    // дата рождения должна быть типа LocalDate, а не LocalDateTime
}
