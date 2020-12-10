package org.example.aerien.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan(basePackages = { "org.example.aerien" }, lazyInit = true)
@EntityScan(basePackages = { "org.example.aerien.domain" })
@EnableJpaRepositories
public class SpringBootAppTest {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootAppTest.class, args);
    }
}

