package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.request.CreateSongLikeRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.services.interfaces.ISongLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("song-like")
public class SongLikeController {

    @Autowired
    private ISongLikeService service;

    @GetMapping("{likeId}")
    public ResponseEntity<BaseResponse> listAllSongsByLikeId(@PathVariable Long likeId){
        BaseResponse baseResponse= service.listAllSongsByLikeId(likeId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("songs/like/{likeId}")
    public void deleteSongsByIdLike(@PathVariable Long likeId){
        service.deleteSongsByIdLike(likeId);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateSongLikeRequest request){
            BaseResponse baseResponse = service.create(request);
            return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }


}
