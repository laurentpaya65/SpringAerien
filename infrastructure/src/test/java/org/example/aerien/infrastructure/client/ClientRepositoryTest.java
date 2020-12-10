package org.example.aerien.infrastructure.client;

import org.checkerframework.checker.units.qual.C;
import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.vol.Vol;
import org.example.aerien.domain.vol.VolRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VolRepository volRepository;

    @Test
    public void find_by_id() {
        //Given
        final Integer clientId = 5;

        //Then
        assertThat(clientRepository.findbyId(clientId)).isNotNull();
    }
    @Test
    public void save() {
        //Given
        final Client client = new Client();
        client.setName("lolo");

        //When
        clientRepository.save(client);

        //Then
        assertThat(clientRepository.findbyId(client.getId())).isNotNull();
        assertThat(clientRepository.findbyId(client.getId()).getName()).isEqualTo("lolo");
    }
}
