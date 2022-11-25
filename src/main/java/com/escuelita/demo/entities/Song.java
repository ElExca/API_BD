package com.escuelita.demo.entities;

import com.escuelita.demo.entities.pivots.SongLike;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "songs")
@Getter
@Setter
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String songUrl;

    @OneToMany(mappedBy = "song")
    private List<SongLike> songLike;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;


    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

}
