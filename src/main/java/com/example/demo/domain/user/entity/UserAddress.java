package com.example.demo.domain.user.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
