package org.example.aerien.domain.client;

import org.example.aerien.domain.common.Entity.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Client extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
