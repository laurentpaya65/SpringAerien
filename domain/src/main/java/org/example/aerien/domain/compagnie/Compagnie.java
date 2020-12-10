package org.example.aerien.domain.compagnie;

import org.example.aerien.domain.common.Entity.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Compagnie extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
