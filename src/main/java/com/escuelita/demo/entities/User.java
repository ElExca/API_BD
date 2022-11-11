package com.escuelita.demo.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 100)

    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name = "like_id", referencedColumnName = "id")
    private Like like;
}
