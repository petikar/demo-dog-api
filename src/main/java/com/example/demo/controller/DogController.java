package com.example.demo.controller;

import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.service.DogService;
import com.example.demo.utils.ApplicationParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    // + TODO Сделать DTO, и маппер/коневертер
    // + Обработка ошибок через ExceptionHandler

    //get может быть не реальный id, пока вручную его проверяю

    //+TODO Через конструктор

    private final DogService service;
    private final ApplicationParameters applicationParameters;

    @Autowired
    public DogController(DogService service, ApplicationParameters applicationParameters) {
        this.service = service;
        this.applicationParameters = applicationParameters;
    }

    @GetMapping
    public ResponseEntity<List<DogDto>> getAll() {

        List<DogDto> dogs = service.findAll();
        // + TODO NOT_FOUND обычно относится к конкретной записи, для списков лучше возвращать пустой список

        return new ResponseEntity<>(dogs, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DogDtoWithComment> getDog(@PathVariable("id") int id) {

        //TODO 1. + Параметры приложения перенести в yaml
        // 2. + Сделать класс для хранения каких-то параметров приложения
        // 3. + Один параметр comment
        // 4. + Значение параметра должно браться из конфигурационного файла
        // 5. + И выдаваться в ответе только этого интерфейса вместе с сущностью dog
//        {
//            "id": 0,
//            "name": "",
//            "age": 0.00,
//            "comment": "значение из конфига"
//        }

        DogDto dog = service.findById(id);
        DogDtoWithComment dogWithComment = new DogDtoWithComment(dog, applicationParameters.getComment());


/*        if (dog == null) {
            throw new ApiRequestException("Custom ApiRequestException: Dog doesn't found by id=" + id);
        } else {
            return new ResponseEntity<>(dog, HttpStatus.OK);
        }*/
        return new ResponseEntity<>(dogWithComment, HttpStatus.OK); //если собака не найдена, то ошибку возвращает сервис. Так можно?
    }

    //проверить что передан валидный пёс
    @PostMapping
    public ResponseEntity<DogDto> createDog(@RequestBody DogDto newDog) {
        service.save(newDog);
        return new ResponseEntity<>(newDog, HttpStatus.CREATED);
    }
}
