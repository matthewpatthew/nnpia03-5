package com.example.nnpiacv02.service.impl;

import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.repository.AppUserRepository;
import com.example.nnpiacv02.service.AppUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppUserServiceImplTest {

    @Mock
    private AppUserRepository userRepositoryMock;

    @InjectMocks
    private AppUserServiceImpl appUserService; // stejné jak to vložit jako parametr konstruktoru

    @BeforeEach
    void setUp() {
        //appUserService = new AppUserServiceImpl(userRepositoryMock);
    }

    @AfterEach
    void tearDown() {
    }

    // v junit 5 jsem nic takoveho nenašel
    //v junit 4 to je @Test(expected = AppUserException.class)
    @Test
    void findUserById() throws AppUserException {
        long userId = 100L;
        AppUser expectedUser = new AppUser();
        expectedUser.setId(userId);
        expectedUser.setUsername("testUser");

        // Mock behavior of the repository
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedUser));

        AppUser foundUser = appUserService.findUserById(userId);

        assertNotNull(foundUser);
        assertEquals(expectedUser, foundUser);
        assertThrows(AppUserException.class, () -> {
            appUserService.findUserById(5L);
        });
    }


}