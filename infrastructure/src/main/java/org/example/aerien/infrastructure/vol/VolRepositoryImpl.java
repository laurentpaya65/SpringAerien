package org.example.aerien.infrastructure.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.common.Exception.NotFoundException;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.vol.Vol;
import org.example.aerien.domain.vol.VolRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VolRepositoryImpl implements VolRepository {

    private final VolJpaRepository volJpaRepository;

    public VolRepositoryImpl(VolJpaRepository volJpaRepository) {
        this.volJpaRepository = volJpaRepository;
    }

    @Override
    public List<Vol> findvolsByClients(List<Client> clients) {
        return  volJpaRepository.findVolsByClientsIn(clients);
    }

    @Override
    public Vol findById(Integer id) {
        return volJpaRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void save(Vol vol) {
        volJpaRepository.save(vol);
    }

    @Override
    public List<Vol> findVolsByCompagnie(Compagnie compagnie) {
        return volJpaRepository.findVolsByCompagnie(compagnie);
    }

    @Override
    public List<Vol> findAll() {
        return volJpaRepository.findAll();
    }
}
