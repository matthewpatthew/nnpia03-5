package com.example.nnpiacv02.mapper;

import com.example.nnpiacv02.dto.AppUserDto;
import com.example.nnpiacv02.entity.AppUser;

import java.util.Date;

public class AppUserMapper {

    public static AppUserDto mapToAppUserDto(AppUser appUser) {
        return new AppUserDto(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.isActive(),
                appUser.getCreationDate(),
                appUser.getUpdateDate()
        );
    }
}