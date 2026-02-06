package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void save (User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User getUserByUsername(String username){
        return repository.findByUsername(username);
    }
}
