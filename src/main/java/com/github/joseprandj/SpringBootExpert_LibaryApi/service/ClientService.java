package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Client;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(Client client){
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        return repository.save(client);
    }

    public Client searchForClientId(String clientId){
        return repository.findByClientId(clientId);
    }
}
