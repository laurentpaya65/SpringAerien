package org.example.aerien.application.compagnie;

import org.example.aerien.domain.common.Exception.AlreadyExistException;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompagnieServiceImpl implements CompagnieService {
    @Autowired
    private CompagnieRepository compagnieRepository;

    @Override
    public Compagnie findOne(Integer id) {
        return compagnieRepository.findById(id);
    }

    @Override
    public Compagnie create(Compagnie compagnie) {
        final Compagnie compagnie1 = compagnieRepository.findById(compagnie.getId());
        if (compagnie1 != null) {
            throw new AlreadyExistException(compagnie.getName()+" existe déjà en base");
        }
        compagnieRepository.save(compagnie);
        return compagnie;
    }

    @Override
    public Compagnie findByCompagnieName(String name) {
        return compagnieRepository.findByName(name);
    }

    @Override
    public List<Compagnie> findAllCompagnie() {
        return compagnieRepository.findAll();
    }
}
