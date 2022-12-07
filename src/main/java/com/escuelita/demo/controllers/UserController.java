package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.request.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;
import com.escuelita.demo.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService service;

    //SEE ALL USER
    @GetMapping
    public ResponseEntity<BaseResponse> list() {
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    //SEE BY ID
    @GetMapping("{id}")
    public GetUserResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    //CREATE USER
    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateUserRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    //UPDATE USER
    @PutMapping("{id}")
    public GetUserResponse update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}