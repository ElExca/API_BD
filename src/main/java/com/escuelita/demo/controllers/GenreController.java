package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.request.CreateGenreRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetArtistResponse;
import com.escuelita.demo.controllers.dtos.response.GetGenreResponse;
import com.escuelita.demo.services.interfaces.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("genre")
@RestController
public class GenreController {
    @Autowired
    private IGenreService service;

    @GetMapping
    public List<GetGenreResponse> list(){return service.list();}

    @GetMapping("{id}")
    public GetGenreResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){service.delete(id);}

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateGenreRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
