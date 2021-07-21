package com.example.demo.controller;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;
import com.example.demo.dto.transfer.DogInfo;
import com.example.demo.dto.transfer.Exist;
import com.example.demo.dto.transfer.FindByName;
import com.example.demo.dto.transfer.New;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotValidException;
import com.example.demo.exception.ResponseException;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService service;

    //+TODO Эта аннотация на конструкторе не нужна
    public DogController(DogService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DogDto>> getAll() {
        List<DogDto> dogs = service.findAll();
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DogDtoWithComment> getDog(@PathVariable("id") int id) throws Exception {
        DogDtoWithComment dog = service.findById(id);
        // + TODO Это не ответственность контроллера делать эту конвертацию
        return new ResponseEntity<>(dog, HttpStatus.OK); //если собака не найдена, то ошибку возвращает сервис. Так можно?
        //TODO Это вопрос того используется ли сервис еще где-то, может ли вернуть null. Если в других частях программы Exception обрабатывается, то можно
    }

    @PostMapping
    @JsonView({Exist.class})
    public ResponseEntity<DogDto> createDog(@Validated(New.class) @RequestBody DogDto newDog) {
        //+ TODO В Dog id - GeneratedValue. А что будет, если он будет передан в DogDto?
        return new ResponseEntity<>(service.save(newDog), HttpStatus.CREATED);
    }

    //+ TODO findByName, тип POST.
    // + На входе body: {"name": "some name"}
    // + На выходе: DogInfoDto

    @PostMapping("/name")
    @JsonView({DogInfo.class})
    public ResponseEntity<List<DogInfoDto>> findByName(@Validated(FindByName.class) @RequestBody DogDto nameForSearch) {
        String name = nameForSearch.getName();
        List<DogInfoDto> dogs = service.findByName(name);
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseException handleMethodArgumentNotValidException(Exception e) {
        return new ResponseException(e.getClass().getSimpleName() + " Данные, переданные в JSON, не валидны " + e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("UTC+7")));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseException handleMethodArgumentTypeMismatchException(Exception e) {
        return new ResponseException(e.getClass().getSimpleName() + " Тип переданных параметров не соответствует ожидаемому. " + e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("UTC+7")));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseException handleHttpMessageNotReadableException(Exception e) {
        return new ResponseException(e.getClass().getSimpleName() + " Ошибка в JSON. Тип переданных параметров не соответствует ожидаемому типу и не мм.б. конвертирован в него " + e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("UTC+7")));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseException handleNotFoundException(Exception e) {
        return new ResponseException(e.getClass().getSimpleName() + e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("UTC+7")));
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseException handleNotValidException(Exception e) {
        return new ResponseException(e.getClass().getSimpleName() +" "+ e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("UTC+7")));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseException handleRuntimeException(Exception e) {
        return new ResponseException(e.getClass().getSimpleName() +" "+ e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("UTC+7")));
    }


}
