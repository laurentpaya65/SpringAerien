package org.example.arien.application.compagnie;

import org.example.aerien.application.compagnie.CompagnieService;
import org.example.aerien.application.compagnie.CompagnieServiceImpl;
import org.example.aerien.domain.common.Exception.AlreadyExistException;
import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CompagnieServiceImpl.class})
public class CompagnieServiceTest {

    @Autowired
    private CompagnieService compagnieService;

    @MockBean
    private CompagnieRepository compagnieRepositoryMock;

    // obtenir une Instance de compagnie
    private Compagnie getCompagnie(Integer id, String name) {
        Compagnie compagnie = new Compagnie();
        compagnie.setName(name);
        compagnie.setId(id);
        return compagnie;
    }
    @Test
    public void create_if_not_exist(){
        //Given
        final Compagnie compagnie = new Compagnie();
        compagnie.setName("Air France");
        when(compagnieRepositoryMock.findById(compagnie.getId())).thenReturn(null);

        //When
        final Compagnie result = compagnieService.create(compagnie);

        //Then
        assertThat(result).isNotNull();
        verify(compagnieRepositoryMock,times(1)).save(compagnie);
    }
    @Test
    public void find_by_name() {
        //Given
        final String name = "Air France";
        final Compagnie compagnie = new Compagnie();

        compagnie.setName(name);
        when(compagnieRepositoryMock.findByName(name)).thenReturn(compagnie);

        //When
        final Compagnie result = compagnieService.findByCompagnieName(name);

        //Then
        assertThat(result.getName()).isEqualTo(name);
    }
    @Test
    public void find_all_compagnie() {
        //Given
        List<Compagnie> compagnies = new ArrayList<>();
        final CompagnieServiceTest c = new CompagnieServiceTest();
        compagnies.add(c.getCompagnie(10,"c1"));
        compagnies.add(c.getCompagnie(11,"c2"));
        compagnies.add(c.getCompagnie(12,"c3"));
        when(compagnieRepositoryMock.findAll()).thenReturn(compagnies);

        //When
        final List<Compagnie> result = compagnieService.findAllCompagnie();

        //Then
        assertThat(result.size()).isEqualTo(3);
    }
}
