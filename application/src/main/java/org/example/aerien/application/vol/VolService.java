package org.example.aerien.application.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.vol.Vol;

import java.util.List;

public interface VolService {

    public Vol findOne(Integer id);

    public Vol create(Vol vol);

    public Vol addOne(Integer compagnieId, List<Integer> clientsId);

    public List<Vol> VolsByCompagnie(Integer compagnieId);

    public List<Client> ClientsByVol(Integer volId);
}
