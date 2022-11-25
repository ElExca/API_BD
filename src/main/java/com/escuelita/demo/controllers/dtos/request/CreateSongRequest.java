package com.escuelita.demo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSongRequest {
    private String name;

    private String songUrl;

    private Long likeId;

    private String artistName;

    private Long genreId;

}
