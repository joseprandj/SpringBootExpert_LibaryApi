package com.github.joseprandj.SpringBootExpert_LibaryApi.security;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.getUserByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found!");

        return org.springframework.security.core.userdetails.User
            .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
            .build();
    }
}
