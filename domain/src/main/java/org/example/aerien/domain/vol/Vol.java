package org.example.aerien.domain.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.common.Entity.AbstractEntity;
import org.example.aerien.domain.compagnie.Compagnie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vol extends AbstractEntity {

    private String Date;

    @ManyToOne
    @JoinColumn(name="COMPAGNIE_ID")
    private Compagnie compagnie;

    @ManyToMany(targetEntity = Client.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Client> clients = new ArrayList<>();

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
