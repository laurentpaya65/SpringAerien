package org.example.aerien.application.client;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.common.Exception.AlreadyExistException;
import org.example.aerien.domain.vol.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findOne(Integer id) {
        return clientRepository.findbyId(id);
    }

    @Override
    public Client create(Client client) {
        Client client1 = clientRepository.findbyId(client.getId());
        if (client1 != null) {
            throw new AlreadyExistException("client existe déjà");
        }
        clientRepository.save(client);
        return client;
    }


}
