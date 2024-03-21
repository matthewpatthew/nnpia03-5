package com.example.nnpiacv02.controller;

import com.example.nnpiacv02.mock.WithMockAdmin;
import com.example.nnpiacv02.mock.WithMockUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockAdmin
    void testExistingUserEndpoint() throws Exception {
        String existingUserId = "1";
        mockMvc.perform(get("/users/admin/{id}", existingUserId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockAdmin
    void testNonExistingUserEndpoint() throws Exception {
        String existingUserId = "0";
        mockMvc.perform(get("/users/admin/{id}", existingUserId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testListOfUsersEndpoint() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testNotLoggedInShouldNotSeeSecuredEndpoint() throws Exception {
        mockMvc.perform(get("/users/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void testLoggedInShouldSeeSecuredEndpoint() throws Exception {
        mockMvc.perform(get("/users/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("user: fake_username with id: 1")));
    }

    @Test
    void testNotLoggedInShouldNotSeeAdminEndpoint() throws Exception {
        mockMvc.perform(get("/users/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void testUserShouldNotSeeAdminEndpoint() throws Exception {
        mockMvc.perform(get("/users/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockAdmin
    void testAdminShouldSeeAdminEndpoint() throws Exception {
        mockMvc.perform(get("/users/admin"))
                .andExpect(status().isOk());
    }
}
