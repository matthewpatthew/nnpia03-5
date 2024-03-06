package com.example.nnpiacv02.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt){
        return UserPrincipal.builder()
                .id(Long.parseLong(jwt.getSubject()))
                .username(jwt.getClaim("u").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if(claim.isNull()||claim.isMissing()){
            return List.of();
        }
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
