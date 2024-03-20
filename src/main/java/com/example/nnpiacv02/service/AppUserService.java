package com.example.nnpiacv02.service;

import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUser> findAllUsers();

    List<AppUser> findAppUserByActive(Boolean active);

    List<AppUser> findUsersByRole(String roleName);

    AppUser findUserByUsername(String username);

    AppUser findUserById(Long id) throws AppUserException;

    AppUser createNewAppUser(AppUserDtoInput appUserDtoInput, PasswordEncoder passwordEncoder);

    AppUser updateAppUser(Long id, AppUserDtoInput appUserDtoInput, PasswordEncoder passwordEncoder) throws AppUserException;

    void deleteAppUser(Long id) throws AppUserException;


}
