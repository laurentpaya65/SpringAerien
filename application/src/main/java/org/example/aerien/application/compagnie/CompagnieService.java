package org.example.aerien.application.compagnie;

import org.example.aerien.domain.compagnie.Compagnie;

import java.util.List;

public interface CompagnieService {
    public Compagnie findOne(Integer id);

    public Compagnie create(Compagnie compagnie);

    public Compagnie findByCompagnieName(String name);

    public List<Compagnie> findAllCompagnie();
}
