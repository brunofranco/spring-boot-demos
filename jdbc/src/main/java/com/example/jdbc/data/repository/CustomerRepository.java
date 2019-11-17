package com.example.jdbc.data.repository;

import com.example.jdbc.data.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {
}
