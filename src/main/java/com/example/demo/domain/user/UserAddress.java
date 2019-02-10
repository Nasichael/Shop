package com.example.demo.domain.user;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class UserAddress {
    @Id
    private int id;
    private String street;
}
