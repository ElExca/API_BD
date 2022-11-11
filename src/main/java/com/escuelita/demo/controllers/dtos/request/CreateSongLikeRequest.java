package com.escuelita.demo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSongLikeRequest {
private Long idSong;

private Long idLike;
}
