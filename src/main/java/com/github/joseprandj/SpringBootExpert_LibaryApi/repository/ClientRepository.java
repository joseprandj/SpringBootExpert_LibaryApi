package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
