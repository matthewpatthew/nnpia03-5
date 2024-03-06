package com.example.nnpiacv02.controller;

import com.example.nnpiacv02.dto.AppUserDto;
import com.example.nnpiacv02.dto.AppUserDtoInput;
import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.exception.AppUserException;
import com.example.nnpiacv02.mapper.AppUserMapper;
import com.example.nnpiacv02.security.JwtIssuer;
import com.example.nnpiacv02.security.LoginRequest;
import com.example.nnpiacv02.security.LoginResponse;
import com.example.nnpiacv02.security.UserPrincipal;
import com.example.nnpiacv02.service.AppUserService;
import jakarta.validation.Valid;
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

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    private final AppUserService appUserService;

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
    public ResponseEntity<AppUserDto> createNewAppUser(@RequestBody @Valid AppUserDtoInput appUserDtoInput) {
        AppUserDto appUserDto = AppUserMapper.mapToAppUserDto(appUserService.createNewAppUser(appUserDtoInput, passwordEncoder));
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

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getId(), principal.getUsername(), roles);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

    @GetMapping("/whoami")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "Ur logged in as user: " + principal.getUsername() + " with id: " + principal.getId();
    }
}
