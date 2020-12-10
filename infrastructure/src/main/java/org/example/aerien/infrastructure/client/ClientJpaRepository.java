package org.example.aerien.infrastructure.client;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.vol.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientJpaRepository extends JpaRepository<Client,Integer> {
}
