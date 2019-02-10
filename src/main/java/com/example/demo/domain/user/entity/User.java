package com.example.demo.domain.user.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "demo_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
}
