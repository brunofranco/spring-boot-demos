package com.example.jdbc.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("CUSTOMER")
public class CustomerEntity {

    @Id
    private UUID id;
    private String name;
    private Date created;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, Date created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", created=" + created +
            '}';
    }
}
