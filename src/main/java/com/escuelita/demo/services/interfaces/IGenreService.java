package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.request.CreateGenreRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetGenreResponse;
import com.escuelita.demo.entities.Genre;

import java.util.List;

public interface IGenreService {

    List<GetGenreResponse> list();

    void delete(Long id);

    BaseResponse create(CreateGenreRequest request);

    Genre findById(Long id);

    GetGenreResponse get(Long id);


}
