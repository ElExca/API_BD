package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.request.CreateSongRequest;
import com.escuelita.demo.controllers.dtos.request.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateSongRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetSongResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;
import com.escuelita.demo.entities.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISongService {

    GetSongResponse get(Long id);

    Song findById(Long id);

BaseResponse list();

    void delete(Long id);


    BaseResponse create(CreateSongRequest request);

    GetSongResponse update(Long id, UpdateSongRequest request);


    BaseResponse upload(MultipartFile file);

}
