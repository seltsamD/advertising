package com.advertising.dashboard.config;


import com.advertising.dashboard.model.UserRole;
import com.advertising.dashboard.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetail implements UserDetails {
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = getAuthorities().stream()
                .filter(role -> ((GrantedAuthority) role).getAuthority().equals(user.getUserRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(UserRole userRole: UserRole.values()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
