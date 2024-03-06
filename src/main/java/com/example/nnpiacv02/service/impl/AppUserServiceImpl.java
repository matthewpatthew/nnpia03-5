package com.example.nnpiacv02.service.impl;

import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.repository.AppUserRepository;
import com.example.nnpiacv02.service.AppUserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppUser> findAppUserByActive(Boolean active) {
        return appUserRepository.findAppUserByActive(active);
    }

    @Override
    public List<AppUser> findUsersByRole(String roleName) {
        return appUserRepository.findUsersByRole(roleName);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser findUserById(Long id) throws AppUserException {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isEmpty()) {
            throw new AppUserException("User does not exist");
        }
        return appUser.get();
    }

    @Override
    public AppUser createNewAppUser(AppUserDtoInput appUserDtoInput) {
        AppUser appUser = new AppUser(
                appUserDtoInput.getUsername(),
                appUserDtoInput.getPassword(),
                appUserDtoInput.isActive(),
                new Date(),
                new Date()
        );
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateAppUser(Long id, AppUserDtoInput appUserDtoInput) throws AppUserException {
        AppUser appUser = findUserById(id);
        appUser.setUsername(appUserDtoInput.getUsername());
        appUser.setActive(appUserDtoInput.isActive());
        appUser.setPassword(appUserDtoInput.getPassword());
        appUser.setUpdateDate(new Date());

        return appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUser(Long id) throws AppUserException {
        AppUser appUser = findUserById(id);
        appUserRepository.delete(appUser);
    }
}
