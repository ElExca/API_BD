package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.request.CreateSongRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateSongRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetSongResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;
import com.escuelita.demo.services.interfaces.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("song")
public class SongController {
    @Autowired
    private ISongService service;

    @GetMapping
    public BaseResponse list() {
        return service.list();
    }

    @PostMapping("upload")
    public ResponseEntity<BaseResponse> upload(@RequestParam MultipartFile file){
        BaseResponse baseResponse= service.upload(file);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateSongRequest request){
        BaseResponse baseResponse= service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @GetMapping("{id}")
    public GetSongResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("{id}")
    public GetSongResponse update(@PathVariable Long id, @RequestBody UpdateSongRequest request) {
        return service.update(id, request);
    }
}
