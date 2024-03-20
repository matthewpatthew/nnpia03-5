package com.example.nnpiacv02.controller;

import com.example.nnpiacv02.dto.AppUserDto;
import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.mapper.AppUserMapper;
import com.example.nnpiacv02.security.JwtIssuer;
import com.example.nnpiacv02.model.LoginRequest;
import com.example.nnpiacv02.model.LoginResponse;
import com.example.nnpiacv02.security.UserPrincipal;
import com.example.nnpiacv02.service.AppUserService;
import com.example.nnpiacv02.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/app-user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<List<AppUserDto>> findAllUsers() {
        List<AppUser> appUsers = appUserService.findAllUsers();
        List<AppUserDto> appUserDto = new ArrayList<>();
        for (AppUser appUser : appUsers) {
            appUserDto.add(AppUserMapper.mapToAppUserDto(appUser));
        }
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AppUserDto> findAppUserById(@PathVariable Long id) throws AppUserException {
        AppUser appUser = appUserService.findUserById(id);
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUser);
        return ResponseEntity.ok(appUserDto);
    }

    @PostMapping
    public ResponseEntity<AppUserDto> createNewAppUser(@RequestBody @Validated AppUserDtoInput appUserDtoInput) {
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUserService.createNewAppUser(appUserDtoInput, passwordEncoder));
        return new ResponseEntity<>(appUserDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUserDto> updateAppUser(@PathVariable("id") Long id,
                                                    @RequestBody @Validated AppUserDtoInput appUserDtoInput) throws AppUserException {
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUserService.updateAppUser(id, appUserDtoInput));
        return ResponseEntity.ok(appUserDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppUser(@PathVariable("id") Long id) throws AppUserException {
        appUserService.deleteAppUser(id);
        return ResponseEntity.ok("User deleted " + id);
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getUsername(), request.getPassword());
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "Ur logged in as user: " + principal.getUsername() + " with id: " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return "Ur ADMIN: " + principal.getUsername() + " with id: " + principal.getUserId();
    }

}
