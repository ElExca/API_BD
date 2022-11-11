package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.SongLike;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
@Getter @Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "artist")
    private List<Song> song;

}
