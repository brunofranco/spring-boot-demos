package com.example.jdbc.data.repository;

import com.example.jdbc.data.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void save() {
        var entity = new CustomerEntity(
            "name",
            new Date()
        );

        customerRepository.save(entity);

        var savedEntity = customerRepository.findById(entity.getId());
        assertThat(savedEntity.isPresent(), is(Boolean.TRUE));
        assertThat(entity.getName(), equalTo(savedEntity.get().getName()));
    }

    @Test
    void update() {
        var entity = new CustomerEntity(
            "name",
            new Date()
        );
        customerRepository.save(entity);

        var entity2 = new CustomerEntity(
            "name updated",
            new Date()
        );
        entity2.setId(entity.getId());
        customerRepository.save(entity2);

        var updatedEntity = customerRepository.findById(entity2.getId());
        assertThat(updatedEntity.isPresent(), is(Boolean.TRUE));
        assertThat(entity2.getName(), equalTo(updatedEntity.get().getName()));
    }
}
