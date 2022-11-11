package com.escuelita.demo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateLikeRequest {
    private Long idUser;
    private Long idLike;

}
