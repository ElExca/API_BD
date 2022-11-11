package com.escuelita.demo.entities.pivots;

import com.escuelita.demo.entities.Like;
import com.escuelita.demo.entities.Song;
import com.escuelita.demo.entities.Artist;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "songs_likes")
@Getter
@Setter
public class SongLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Song song;

    @ManyToOne
    private Like like;
}
