package org.example.aerien.infrastructure.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.vol.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolJpaRepository extends JpaRepository<Vol,Integer> {

    List<Vol> findVolsByCompagnie(Compagnie compagnie);

    List<Vol> findVolsByClientsIn(List<Client> clients);

 }
