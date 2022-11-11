package com.escuelita.demo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetSongLikeResponse {
private Long id;
private SongResponse song;
private LikeResponse like;
}
