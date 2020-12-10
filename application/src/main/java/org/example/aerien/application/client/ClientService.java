package org.example.aerien.application.client;

import org.example.aerien.domain.client.Client;

import java.util.List;

public interface ClientService {

    public Client findOne(Integer id);

    public Client create(Client client);
}
