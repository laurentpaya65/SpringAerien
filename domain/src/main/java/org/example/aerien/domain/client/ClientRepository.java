package org.example.aerien.domain.client;


import org.example.aerien.domain.vol.Vol;

import java.util.List;

public interface ClientRepository {

    public Client findbyId(Integer id);

    public List<Client> findbyIds(List<Integer> ids);

    public void save(Client client);

}
