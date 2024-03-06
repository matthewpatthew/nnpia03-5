package com.example.nnpiacv02.controller;

import com.example.nnpiacv02.dto.AppUserDto;
import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.mapper.AppUserMapper;
import com.example.nnpiacv02.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/app-user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping({"/{id}"})
    public ResponseEntity<AppUserDto> findAppUserById(@PathVariable Long id) throws AppUserException {
        AppUser appUser = appUserService.findUserById(id);
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUser);
        return ResponseEntity.ok(appUserDto);
    }

    @PostMapping
    public ResponseEntity<AppUserDto> createNewAppUser(@RequestBody @Valid AppUserDtoInput appUserDtoInput) {
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUserService.createNewAppUser(appUserDtoInput));
        return new ResponseEntity<>(appUserDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUserDto> updateAppUser(@PathVariable("id") Long id,
                                                    @RequestBody @Valid AppUserDtoInput appUserDtoInput) throws AppUserException {
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUserService.updateAppUser(id, appUserDtoInput));
        return ResponseEntity.ok(appUserDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppUser(@PathVariable("id") Long id) throws AppUserException {
        appUserService.deleteAppUser(id);
        return ResponseEntity.ok("User deleted " + id);
    }
}
