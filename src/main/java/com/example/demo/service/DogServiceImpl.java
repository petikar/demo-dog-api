package com.example.demo.service;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;
import com.example.demo.dto.mapper.DogInfoDtoMapper;
import com.example.demo.dto.mapper.DogMapperImpl;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotValidException;
import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import com.example.demo.utils.ConfigProperties;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DogServiceImpl implements DogService {

    private final DogRepository repository;
    private final ModelMapper modelMapper;
    private final ConfigProperties configProperties;
    private final DogInfoDtoMapper dogInfoDtoMapper;
    private final DogMapperImpl dogMapper;

    public DogServiceImpl(DogRepository repository, ModelMapper modelMapper, ConfigProperties configProperties, DogInfoDtoMapper dogInfoDtoMapper, DogMapperImpl dogMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.configProperties = configProperties;
        this.dogInfoDtoMapper = dogInfoDtoMapper;
        this.dogMapper = dogMapper;
    }

    @Override
    public DogDto save(DogDto newDog) {
        //+ TODO Сервис не должен возвращать Entity
        //Dog dog = modelMapper.map(newDog, Dog.class); //вот тут localdatetime и localdate не дружат, => надо самой настроить конвертер
        Dog dog = dogMapper.toEntity(newDog);
        //return modelMapper.map(repository.save(dog), DogDto.class);
        return dogMapper.toDto(repository.save(dog));
    }

    @Override
    public List<DogDto> findAll() {
        List<Dog> dogs = repository.findAll();
        //+ TODO А если, вдруг, классы будут не полностью совместимы, см. findByName?
        //List<DogDto> dogsDto = dogs.stream().map(dog -> modelMapper.map(dog, DogDto.class)).collect(Collectors.toList());
        List<DogDto> dogsDto = dogs.stream().map(dogMapper::toDto).collect(Collectors.toList());
        return dogsDto;
    }

    @Override
    public DogDtoWithComment findById(int id) throws CustomException {
        if (id < 1) {
            //+TODO Это абсолютно разные типы ошибок
            throw new NotValidException("id must be an integer greater than 1, id = " + id + " doesn't exist");
        }
        //TODO +Это абсолютно разные типы ошибок - тут not found 404
        Dog dog = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found Dog by id=" + id));
        DogDtoWithComment dogDtoWithComment = new DogDtoWithComment(dog, configProperties.getComment());
        return dogDtoWithComment;
    }

    @Override
    public List<DogInfoDto> findByName(String name) {
        List<Dog> dogs = repository.findByName(name);
        List<DogInfoDto> dogsInfoDto = dogs.stream().map(dogInfoDtoMapper::toDogInfoDto).collect(Collectors.toList());
        return dogsInfoDto;
    }

    //TODO findByName +
    // + Должен возвращать новый DTO DogInfoDto:
    // + вместо age должен быть Enum: Young (age <= 3), Middle-Aged (age <= 10), Older
    // + дата рождения должна быть типа LocalDate, а не LocalDateTime
}
