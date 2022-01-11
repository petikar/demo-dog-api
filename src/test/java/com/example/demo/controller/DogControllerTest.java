package com.example.demo.controller;

import com.example.demo.dto.Age;
import com.example.demo.dto.DogDto;
import com.example.demo.dto.DogDtoWithComment;
import com.example.demo.dto.DogInfoDto;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(DogController.class)
class DogControllerTest {

    @MockBean
    private DogService service;

    @Autowired
    private MockMvc mockMvc; //с помощью него делаем запрос

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldReturnListOfDogs() throws Exception {

        DogDto dog = new DogDto();

        dog.setName("Tayga");
        dog.setId(1);
        dog.setAge(12);
        dog.setBirthday(LocalDateTime.of(2012, 6, 9, 12, 12));

        List<DogDto> list = new ArrayList<>();
        list.add(dog);

        when(service.findAll()).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/dogs")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tayga"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthday").value("2012-06-09T12:12:00"));
    }

    @Test
    void shouldReturnOneDogDtoWithCommentById() throws Exception {

        DogDtoWithComment dog = new DogDtoWithComment();
        dog.setId(1);
        dog.setName("Tayga");
        dog.setAge(12);
        dog.setBirthday(LocalDateTime.of(2012, 6, 9, 12, 12));
        dog.setComment("comment");

        when(service.findDogDtoWithCommentById(1)).thenReturn(dog);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/dogs/1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tayga"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2012-06-09T12:12:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("comment"));
    }

    @Test
    void shouldCreateDog() throws Exception {

        DogDto dog = new DogDto();
        dog.setId(1);
        dog.setName("Tayga");
        dog.setAge(12);
        dog.setBirthday(LocalDateTime.of(2012, 6, 9, 12, 12));

        String jsonDog = mapper.writeValueAsString(dog);
        System.out.println(jsonDog);

        when(service.save(any(DogDto.class))).thenReturn(dog);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/dogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDog)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tayga"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2012-06-09T12:12:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }

    @Test
    void shoulFindDogByName() throws Exception {

        LocalDate localDate = LocalDate.of(2012, 6, 9);

        DogInfoDto dog = new DogInfoDto();

        dog.setId(1);
        dog.setName("Tayga");
        dog.setAgeEnum(Age.MIDDLE_AGED);
        dog.setBirthdayLocalDate(localDate);

        List<DogInfoDto> list = new ArrayList<>();
        list.add(dog);

        when(service.findByName("Tayga")).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/dogs/name")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"Tayga\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tayga"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("MIDDLE_AGED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthday").value("2012-06-09"));
    }
}