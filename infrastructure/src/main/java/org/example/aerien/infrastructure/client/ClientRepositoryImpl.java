package org.example.aerien.infrastructure.client;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.common.Exception.NotFoundException;
import org.example.aerien.domain.vol.Vol;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    public ClientRepositoryImpl(ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public Client findbyId(Integer id) {
        return clientJpaRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public List<Client> findbyIds(List<Integer> ids) {
        return clientJpaRepository.findAllById(ids);
    }

    @Override
    public void save(Client client) {
        clientJpaRepository.save(client);
    }

}
