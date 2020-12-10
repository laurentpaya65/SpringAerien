package org.example.arien.application.Vol;

import org.example.aerien.application.client.ClientService;
import org.example.aerien.application.client.ClientServiceImpl;
import org.example.aerien.application.compagnie.CompagnieService;
import org.example.aerien.application.compagnie.CompagnieServiceImpl;
import org.example.aerien.application.vol.VolService;
import org.example.aerien.application.vol.VolServiceImpl;
import org.example.aerien.domain.client.Client;
import org.example.aerien.domain.client.ClientRepository;
import org.example.aerien.domain.common.Exception.AlreadyExistException;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.example.aerien.domain.vol.Vol;
import org.example.aerien.domain.vol.VolRepository;
import org.example.arien.application.compagnie.CompagnieServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VolServiceImpl.class,CompagnieServiceImpl.class, ClientServiceImpl.class})
public class VolServiceTest {

    @Autowired
    private VolService volService;
    @Autowired
    private CompagnieService compagnieService;
    @Autowired
    private ClientService clientService;

    @MockBean
    private VolRepository volRepositoryMock;
    @MockBean
    private CompagnieRepository compagnieRepositoryMock;
    @MockBean
    private ClientRepository clientRepositoryMock;

    private List<Client> getClients() {
        Client client1 = new Client();
        client1.setName("client1");
        client1.setId(10);
        Client client2 = new Client();
        client2.setName("client2");
        client2.setId(11);
        Client client3 = new Client();
        client3.setName("client3");
        client3.setId(12);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        return clients;
    }
    private Compagnie getCompagnie() {
        Compagnie compagnie = new Compagnie();
        compagnie.setName("Air France");
        compagnie.setId(10);
        return compagnie;
    }
    private Vol getVol(Compagnie compagnie,List<Client> clients,Integer volId,String date) {
        Vol vol = new Vol();
        vol.setDate(date);
        vol.setId(volId);
        vol.setClients(clients);
        vol.setCompagnie(compagnie);
        return vol;
    }

    @Test
    public void create_vol_if_non_exist() {
        //Given
        final Vol vol = new Vol();
        vol.setId(10);
        vol.setDate("2020-12-10");
        when(volRepositoryMock.findById(10)).thenReturn(null);

        //When
        final Vol result = volService.create(vol);

        //Then
        assertThat(result).isNotNull();
        verify(volRepositoryMock,times(1)).save(vol);
    }
    @Test
    public void create_vol_if_exist() {
        //Given
        final Vol vol = new Vol();
        vol.setId(10);
        vol.setDate("2020-12-10");
        when(volRepositoryMock.findById(10)).thenReturn(vol);

        //When
        final Vol result;
        try {
            result = volService.create(vol);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistException.class);
        }

        //Then
        verify(volRepositoryMock,never()).save(vol);
    }
    @Test
    public void addOne_vol() {
        //Given
        final VolServiceTest volServiceTest = new VolServiceTest();
        final Vol vol = new Vol();
        List<Integer> listId = Arrays.asList(new Integer[] { 10, 11, 12 });
        vol.setId(1);
        vol.setDate("2020-12-10");
        vol.setClients(volServiceTest.getClients());
        vol.setCompagnie(volServiceTest.getCompagnie());
        when(compagnieRepositoryMock.findById(10)).thenReturn(volServiceTest.getCompagnie());
        when(clientRepositoryMock.findbyIds(listId)).thenReturn(volServiceTest.getClients());

        //When
        final Vol result = volService.addOne(10,listId);

        //Then
        verify(volRepositoryMock,times(1)).save(any());
        assertThat(result.getClients().size()).isEqualTo(3);
    }
    @Test
    public void find_vols_by_compagnie() {
        //Given
        Integer compagnieId = 10;
        final VolServiceTest volServiceTest = new VolServiceTest();
        final Compagnie compagnie = volServiceTest.getCompagnie();
        final Vol vol1 = volServiceTest.getVol(compagnie, volServiceTest.getClients(),compagnieId,"2020-12-20");
        final Vol vol2 = volServiceTest.getVol(compagnie, volServiceTest.getClients(),compagnieId,"2020-12-25");
        List<Vol> vols = new ArrayList<>();
        vols.add(vol1);
        vols.add(vol2);

        when(compagnieRepositoryMock.findById(compagnieId)).thenReturn(compagnie);
        when(volRepositoryMock.findVolsByCompagnie(compagnie)).thenReturn(vols);

        //When
        final List<Vol> result = volService.VolsByCompagnie(compagnie.getId());

        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(1).getDate()).isEqualTo("2020-12-25");
    }
    @Test
    public void find_clients_by_vol() {
        //Given
        final VolServiceTest volServiceTest = new VolServiceTest();
        final Compagnie compagnie = volServiceTest.getCompagnie();
        final Vol vol = volServiceTest.getVol(compagnie, volServiceTest.getClients(),10,"2020-12-20");
        when(volRepositoryMock.findById(10)).thenReturn(vol);

        //When
        final List<Client> result = volService.ClientsByVol(10);

        //Then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(1).getName()).isEqualTo("client2");
    }
}
