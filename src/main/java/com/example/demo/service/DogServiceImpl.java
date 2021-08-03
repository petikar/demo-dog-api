package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;
import com.example.demo.dto.mapper.DogMapper;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotValidException;
import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DogServiceImpl implements DogService {

    private final DogRepository repository;
    private final DogMapper mapper;

    public DogServiceImpl(DogRepository repository, DogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DogDto save(DogDto newDog) {
        //+ TODO Сервис не должен возвращать Entity
        Dog dog = mapper.newDogDtoToDog(newDog);
        return mapper.dogToDogDto(repository.save(dog));
    }

    @Override
    public List<DogDto> findAll() {
        List<Dog> dogs = repository.findAll();
        //+ TODO А если, вдруг, классы будут не полностью совместимы, см. findByName?
        //List<DogDto> dogsDto = dogs.stream().map(dog -> modelMapper.map(dog, DogDto.class)).collect(Collectors.toList());
        return dogs.stream().map(mapper::dogToDogDto).collect(Collectors.toList());
    }

    @Override
    public DogDtoWithComment findDogDtoWithCommentById(int id) throws CustomException {
        if (id < 1) {
            //+TODO Это абсолютно разные типы ошибок
            throw new NotValidException("id must be an integer greater than 1, id = " + id + " doesn't exist");
        }
        //TODO +Это абсолютно разные типы ошибок - тут not found 404
        Dog dog = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found Dog by id=" + id));

        return mapper.dogToDogDtoWithComment(dog);
    }

    @Override
    public List<DogInfoDto> findByName(String name) {
        List<Dog> dogs = repository.findByName(name);
        return dogs.stream().map(mapper::dogToDogInfoDto).collect(Collectors.toList());
    }

    //TODO findByName +
    // + Должен возвращать новый DTO DogInfoDto:
    // + вместо age должен быть Enum: Young (age <= 3), Middle-Aged (age <= 10), Older
    // + дата рождения должна быть типа LocalDate, а не LocalDateTime
}
