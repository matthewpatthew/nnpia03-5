package com.example.nnpiacv02.service.impl;

import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AppUserServiceImpH2lTest {

    @Autowired
    private AppUserServiceImpl appUserService;
    @Autowired
    private AppUserRepository appUserRepository;
    @MockBean
    PasswordEncoder passwordEncoder;
    private AppUser savedUser;

    @BeforeEach
    void setUp() {
        AppUserDtoInput appUserDtoInput = new AppUserDtoInput("Username", "Password", true);
        savedUser = appUserService.createNewAppUser(appUserDtoInput, passwordEncoder);
    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void findUserById() throws AppUserException {
        AppUser foundUser = appUserService.findUserById(savedUser.getId());

        assertNotNull(foundUser);
        assertEquals(savedUser.getUsername(), foundUser.getUsername());
        assertEquals(savedUser.getPassword(), foundUser.getPassword());
        assertThrows(AppUserException.class, () -> appUserService.findUserById(9999L));
    }
}