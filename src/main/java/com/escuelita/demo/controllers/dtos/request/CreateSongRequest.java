package com.escuelita.demo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSongRequest {
    private String name;

    private String duration;

    private String date;

    private String songUrl;

    private Long likeId;

    private Long artistId;

    private Long genreId;

}
