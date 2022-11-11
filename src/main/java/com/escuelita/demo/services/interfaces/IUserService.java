package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.request.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;

import java.util.List;

public interface IUserService{

    GetUserResponse get(Long id);

    List<GetUserResponse> list();

    void delete(Long id);

    BaseResponse create(CreateUserRequest request);

    GetUserResponse update(Long id, UpdateUserRequest request);

}
