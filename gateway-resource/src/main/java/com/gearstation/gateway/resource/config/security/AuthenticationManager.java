package com.gearstation.gateway.resource.config.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: AuthenticationManager <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-08-06 06:39 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        String username;
        try {
            username = authToken;
        } catch (Exception e) {
            username = null;
        }
        if (username != null) {
            List<Role> roles = new ArrayList<>();
            if (username.equals("user")) {
                roles.add(Role.ROLE_USER);
            } else {
                roles.add(Role.ROLE_ADMIN);
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
            );
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
