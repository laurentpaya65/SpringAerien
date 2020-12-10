package org.example.aerien.application.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.common.Exception.AlreadyExistException;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.example.aerien.domain.vol.Vol;
import org.example.aerien.domain.vol.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VolServiceImpl implements VolService {
    @Autowired
    private VolRepository volRepository;

    @Autowired
    private CompagnieRepository compagnieRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Vol findOne(Integer id) {
        return volRepository.findById(id);
    }

    @Override
    public Vol create(Vol vol) {
        final Vol vol1 = volRepository.findById(vol.getId());
        if (vol1 != null) {
            throw new AlreadyExistException();
        }
        volRepository.save(vol);
        return vol;
    }

    @Override
    public Vol addOne(Integer compagnieId, List<Integer> clientsId) {
        final Vol vol = new Vol();
        vol.setCompagnie(compagnieRepository.findById(compagnieId));
        List<Client> clients = clientRepository.findbyIds(clientsId);
        vol.setClients(clients);
        volRepository.save(vol);
        return vol;
    }

    @Override
    public List<Vol> VolsByCompagnie(Integer compagnieId) {
        return volRepository.findVolsByCompagnie(compagnieRepository.findById(compagnieId));
    }

    @Override
    public List<Client> ClientsByVol(Integer volId) {
        final Vol vol = volRepository.findById(volId);
        return vol.getClients();
    }
}
