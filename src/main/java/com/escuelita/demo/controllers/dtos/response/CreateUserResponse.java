package com.escuelita.demo.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserResponse {
    private Long id;
    private String email;
    private Long like;
}
