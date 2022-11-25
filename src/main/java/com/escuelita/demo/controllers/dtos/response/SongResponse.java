package com.escuelita.demo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SongResponse {

    private Long id;

    private String name;

    private String songUrl;

    private ArtistResponse artist;

    private GenreResponse genre;
}
