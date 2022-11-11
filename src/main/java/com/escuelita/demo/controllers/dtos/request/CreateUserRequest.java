package com.escuelita.demo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class CreateUserRequest {
    private String name;

    @Email
    private String email;


    @NotNull
    @NotBlank
    private String password;
}
