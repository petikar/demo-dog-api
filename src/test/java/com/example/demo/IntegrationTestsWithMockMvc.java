package com.example.demo;

import com.example.demo.dto.DogDto;
import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTestsWithMockMvc {

    //+ TODO Тут бы какой-нибудь тестик

    @Autowired
    private DogService dogService;
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void resetDB() {
        dogRepository.deleteAll();
    }

/*    private DogDto createDogDto(int id, String name, int age) {
        DogDto newDogDto = new DogDto(id, name, age);
        dogService.save(newDogDto);
        return newDogDto;
    }*/

    /*private DogDto createDogDto(String name, int age) {
        DogDto newDogDto = new DogDto();
        newDogDto.setName(name);
        newDogDto.setAge(age);
        Dog dog = dogService.save(newDogDto);
        newDogDto.setId(dog.getId());
        return newDogDto;
    }

    @Test
    public void PostDogsControllerMockMvcIntegrationTest() throws Exception {
        DogDto dogDto = createDogDto("Tay", 1);
        int id = dogDto.getId();
        mockMvc.perform(post("/dogs")
                .content(objectMapper.writeValueAsString(new DogDto(id, "Tay", 1)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.age").value(1))
                .andExpect(jsonPath("$.name").value("Tay"));
    }

    @Test
    public void GetDogsIdControllerMockMvcIntegrationTest() throws Exception {
        int id = createDogDto("Tay", 1).getId();
        mockMvc.perform(get("/dogs/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Tay"))
                .andExpect(jsonPath("$.age").value(1))
                .andExpect(jsonPath("$.comment").value("comment for dog"));
    }

    @Test
    public void GetDogsControllerMockMvcIntegrationTest() throws Exception {
        DogDto dogDto = createDogDto("Tay", 1);
        DogDto dogDto2 = createDogDto("Tayga", 2);

        mockMvc.perform(get("/dogs"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Arrays.asList(dogDto, dogDto2))));
        ;
    }*/
}
