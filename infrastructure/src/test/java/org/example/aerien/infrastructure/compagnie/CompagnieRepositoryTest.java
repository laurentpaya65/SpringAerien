package org.example.aerien.infrastructure.compagnie;

import org.example.aerien.domain.compagnie.Compagnie;
import org.example.aerien.domain.compagnie.CompagnieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompagnieRepositoryTest {

    @Autowired
    private CompagnieRepository compagnieRepository;

    @Test
    public void find_compagnie() {
        //Given
        final Integer compagnieId = 1;

        //When

        //Then
        assertThat(compagnieRepository.findById(compagnieId)).isNotNull();
        assertThat(compagnieRepository.findById(compagnieId).getName()).isEqualTo("NAME1");

    }
    @Test
    public void find_all_compagnie() {
        //Then
        assertThat(compagnieRepository.findAll().size()).isEqualTo(3);
    }
    @Test
    public void save_compagnie() {
        //Given
        final Compagnie compagnie = new Compagnie();
        compagnie.setName("Lufthansa");

        //When
        compagnieRepository.save(compagnie);
        System.out.println("compagnie getId="+compagnie.getId());

        //Then
        assertThat(compagnieRepository.findById(compagnie.getId())).isNotNull();
        assertThat(compagnieRepository.findAll().size()).isEqualTo(4);
    }
    @Test
    public void find_compagnie_by_name() {
        //Given
        final Compagnie compagnie1 = new Compagnie();
        compagnie1.setName("Lufthansa");
        final Compagnie compagnie2 = new Compagnie();
        compagnie1.setName("Air France");

        //When
        compagnieRepository.save(compagnie1);
        compagnieRepository.save(compagnie2);

        //Then
        assertThat(compagnieRepository.findAll().size()).isEqualTo(5);
    }
}
