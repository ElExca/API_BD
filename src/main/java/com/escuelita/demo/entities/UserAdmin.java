package com.escuelita.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 100)

    private String email;

    private String password;
}
