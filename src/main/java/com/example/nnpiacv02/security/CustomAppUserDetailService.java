package com.example.nnpiacv02.security;

import com.example.nnpiacv02.entity.AppUser;
import com.example.nnpiacv02.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomAppUserDetailService implements UserDetailsService {

    private final AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserService.findUserByUsername(username);

        List<SimpleGrantedAuthority> userRoles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .authorities(userRoles)
                .password(user.getPassword())
                .build();

    }
}
