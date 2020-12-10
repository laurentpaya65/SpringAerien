package org.example.aerien.domain.compagnie;

import java.util.List;

public interface CompagnieRepository {

    public Compagnie findById(Integer id);

    public void save(Compagnie compagnie);

    public Compagnie findByName(String name);

    public List<Compagnie> findAll();
}
