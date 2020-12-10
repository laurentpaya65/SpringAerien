package org.example.aerien.infrastructure.vol;

import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.example.aerien.domain.vol.Vol;
import org.example.aerien.domain.vol.VolRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VolRepositoryTest {

    @Autowired
    private VolRepository volRepository;

    @Autowired
    private CompagnieRepository compagnieRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void find_by_id() {
        //Given
        final Integer volId = 2;
        //When

        //Then
        assertThat(volRepository.findById(volId)).isNotNull();
        assertThat(volRepository.findById(volId).getDate()).isEqualTo("2020-12-02");
    }
    @Test
    public void save() {
        //Given
        final Vol vol = new Vol();
        vol.setDate("2020-12-06");

        final Client client1 = clientRepository.findbyId(2);
        final Client client2 = clientRepository.findbyId(3);
        List<Client> clientLIST = new ArrayList<>();
        clientLIST.add(client1);
        clientLIST.add(client2);
        vol.setClients(clientLIST);

        final Compagnie compagnie = compagnieRepository.findById(3);
        vol.setCompagnie(compagnie);

        //When
        volRepository.save(vol);
        System.out.println("vol getId="+vol.getId());
        //Then
        assertThat(volRepository.findById(vol.getId())).isNotNull();
    }
    @Test
    public void find_by_compagnie() {
        //Given
        final Integer compagnieId = 2;
        final Compagnie compagnie = compagnieRepository.findById(compagnieId);

        //When
        List<Vol> vols = volRepository.findVolsByCompagnie(compagnie);
//        System.out.println("date vol="+vols.get(0).getDate());
//        System.out.println("date vol="+vols.get(1).getDate());

        //Then
        assertThat(volRepository.findVolsByCompagnie(compagnie).size()).isEqualTo(2);
    }
    @Test
    public void find_by_clients() {
        //Given
        final Client client1 = clientRepository.findbyId(2);
        final Client client2 = clientRepository.findbyId(3);
        final Client client5 = clientRepository.findbyId(5);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client5);

        //When
        List<Vol> vols = volRepository.findvolsByClients(clients);
        System.out.println("date vol="+vols.get(0).getDate());
        System.out.println("date vol="+vols.get(1).getDate());
        System.out.println("date vol="+vols.get(2).getDate());
        System.out.println("date vol="+vols.get(3).getDate());

        //Then
        assertThat(volRepository.findvolsByClients(clients).size()).isEqualTo(4);
    }
}
