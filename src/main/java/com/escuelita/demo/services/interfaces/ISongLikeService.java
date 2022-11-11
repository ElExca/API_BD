package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.request.CreateSongLikeRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.SongResponse;

import java.util.List;

public interface ISongLikeService {
    BaseResponse create(CreateSongLikeRequest request);
    BaseResponse listAllSongsByLikeId(Long likeId);

    void deleteSongsByIdLike(Long likeId);


}
