package com.example.nnpiacv02.controller;

import com.example.nnpiacv02.service.AppUserService;
import com.example.nnpiacv02.service.impl.AppUserServiceImpl;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class AppUserControllerTest {

    //private final String BASE_URL = "http://127.0.0.1:9000/api/v1/app-user/";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testExistingUserEndpoint() throws Exception {
        String userId = "1";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/app-user/{id}", userId))
                .andExpect(status().isOk());
    }

    @Test
    public void testNonExistingUserEndpoint() throws Exception {
        String userId = "8"; // ID neexistujícího uživatele

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/app-user/{id}", userId))
                .andExpect(status().isNotFound());
    }

//    @Test
//    public void testExistingUserEndpoint() throws Exception {
//        String userId = "8"; //
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(BASE_URL + userId);
//
//        HttpResponse response = httpClient.execute(request);
//        int statusCode = response.getCode();
//
//        assertEquals(404, statusCode);
//    }


}