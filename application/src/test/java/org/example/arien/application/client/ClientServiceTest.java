package org.example.arien.application.client;

import org.example.aerien.application.client.ClientService;
import org.example.aerien.application.client.ClientServiceImpl;
import org.example.aerien.application.compagnie.CompagnieServiceImpl;
import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.common.Exception.AlreadyExistException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClientServiceImpl.class})
public class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @MockBean
    ClientRepository clientRepositoryMock;

    @Test
    public void create_if_NOT_exist() {
        //Given
        final Client client = new Client();
        client.setId(10);
        client.setName("lolo");
        when(clientRepositoryMock.findbyId(client.getId())).thenReturn(null);

        //when
        final Client result = clientService.create(client);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("lolo");
        verify(clientRepositoryMock,times(1)).save(client);
    }
    @Test
    public void create_if_exist() {
        //Given
        final Client client = new Client();
        client.setId(10);
        client.setName("lolo");
        when(clientRepositoryMock.findbyId(client.getId())).thenReturn(client);

        //when
        Client result = null;
        try {
            result = clientService.create(client);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistException.class);
        }

        //Then
        assertThat(result).isNull();
        verify(clientRepositoryMock,never()).save(client);
    }

}
