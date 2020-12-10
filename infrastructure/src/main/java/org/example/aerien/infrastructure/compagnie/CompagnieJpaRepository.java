package org.example.aerien.infrastructure.compagnie;

import org.example.aerien.domain.compagnie.Compagnie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompagnieJpaRepository extends JpaRepository<Compagnie, Integer> {

    Compagnie findByName(String name);
}
