package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.SongLike;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "likes")
@Getter
@Setter

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "like")
    private User user;

    @OneToMany(mappedBy = "like")
    private List<SongLike> songLike;


}
