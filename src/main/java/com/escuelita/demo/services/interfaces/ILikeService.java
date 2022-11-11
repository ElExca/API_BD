package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.entities.Like;

public interface ILikeService {

    Like create();

    BaseResponse get(Long id);

    BaseResponse list();

    void delete(Long id);

    Like findById(Long id);
}
