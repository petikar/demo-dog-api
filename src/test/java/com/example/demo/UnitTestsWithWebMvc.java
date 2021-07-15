package com.example.demo;

import com.example.demo.controller.DogController;
import com.example.demo.dto.DogDto;
import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DogController.class)
public class UnitTestsWithWebMvc {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DogService dogService;

/*    @Test
    public void postDogsControllerMockMvcUnitTest() throws Exception {
        DogDto dogDto = new DogDto(1, "Tay", 3);
        Dog dog = new Dog("Tay", 3);
        dog.setId(1);

        Mockito.when(dogService.save(Mockito.any())).thenReturn(dog);//!!!!!разобраться здесь

        mockMvc.perform(post("/dogs")
                .content(objectMapper.writeValueAsString(dogDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(dog)));
    }*/
}
