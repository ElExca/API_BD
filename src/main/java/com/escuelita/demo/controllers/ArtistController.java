package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.request.CreateArtistRequest;
import com.escuelita.demo.controllers.dtos.request.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateArtistRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetArtistResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;
import com.escuelita.demo.services.interfaces.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("artist")
public class ArtistController {
    @Autowired
    IArtistService service;
    @GetMapping
    public List<GetArtistResponse> list() {
        return service.list();
    }

    //SEE BY ID
    @GetMapping("{id}")
    public GetArtistResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    //CREATE USER
    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateArtistRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    //UPDATE USER
    @PutMapping("{id}")
    public GetArtistResponse update(@PathVariable Long id, @RequestBody UpdateArtistRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
