package org.example.aerien.infrastructure.compagnie;


import org.example.aerien.domain.common.Exception.NotFoundException;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompagnieRepositoryImpl implements CompagnieRepository {

    private final CompagnieJpaRepository compagnieJpaRepository;

    public CompagnieRepositoryImpl(CompagnieJpaRepository compagnieJpaRepository) {
        this.compagnieJpaRepository = compagnieJpaRepository;
    }

    @Override
    public Compagnie findById(Integer id) {
        return compagnieJpaRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void save(Compagnie compagnie) {
        compagnieJpaRepository.save(compagnie);
    }

    @Override
    public Compagnie findByName(String name) {
        return compagnieJpaRepository.findByName(name);
    }

    @Override
    public List<Compagnie> findAll() {
        return compagnieJpaRepository.findAll();
    }
}
