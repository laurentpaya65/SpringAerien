package org.example.aerien.domain.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.compagnie.Compagnie;

import java.util.List;
import java.util.Optional;

public interface VolRepository  {

    public Vol findById(Integer id);

    public void save(Vol vol);

    public List<Vol> findVolsByCompagnie(Compagnie compagnie);

    public List<Vol> findvolsByClients(List<Client> clients);

    public List<Vol> findAll();
}
