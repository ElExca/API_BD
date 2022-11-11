package com.escuelita.demo.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class UpdateUserRequest {
    private String name;
@Email
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$")
    private String password;
}
