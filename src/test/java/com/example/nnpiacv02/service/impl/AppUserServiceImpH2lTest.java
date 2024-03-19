package com.example.nnpiacv02.service.impl;

import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppUserServiceImpH2lTest {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser savedUser;

    @BeforeEach
    void setUp() {
        AppUserDtoInput appUserDtoInput = new AppUserDtoInput("Username", "Password", true);
        savedUser = appUserService.createNewAppUser(appUserDtoInput, passwordEncoder());
    }

    @AfterEach
    void tearDown() {
        System.out.println(appUserRepository.findById(savedUser.getId()).get().getId());
        //appUserRepository.deleteAll();
    }

    @Test
    void findUserById() throws AppUserException {
        AppUser foundUser = appUserService.findUserById(savedUser.getId());

        assertNotNull(foundUser);
        assertEquals(savedUser.getUsername(), foundUser.getUsername());
        assertThrows(AppUserException.class, () -> appUserService.findUserById(9999L));
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}