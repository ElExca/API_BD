package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.request.CreateArtistRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateArtistRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetArtistResponse;
import com.escuelita.demo.entities.Artist;

import java.util.List;

public interface IArtistService {

    GetArtistResponse get(Long id);

    List<GetArtistResponse> list();

    void delete(Long id);

    BaseResponse create(CreateArtistRequest request);

    GetArtistResponse update(Long id, UpdateArtistRequest request);

    Artist findById(Long id);

    BaseResponse get(String name);

    Artist findByName(String name);
}
