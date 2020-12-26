package com.gearstation.gateway.resource.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gearstation.gateway.resource.config.security.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: Data transfer object of user Auth entity <br>
 * Copyright © 2019 www.gear-station.com <br>
 * CreateTime: 2019-08-18 11:28 <br>
 *
 * @author packy <br>
 * @version 1.0.1 <br>
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO implements UserDetails {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private List<Role> roles;

    @Setter
    private Boolean isAccountNonExpired;

    @Setter
    private Boolean isAccountNonLocked;

    @Setter
    private Boolean isCredentialsNonExpired;


    @Setter
    private Boolean isEnabled;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
    }
}

