package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.request.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;
import com.escuelita.demo.controllers.dtos.response.LikeResponse;
import com.escuelita.demo.entities.Like;
import com.escuelita.demo.entities.User;
import com.escuelita.demo.entities.projections.UserProjection;
import com.escuelita.demo.repositories.IUserRepository;
import com.escuelita.demo.security.UserDetailsImpl;
import com.escuelita.demo.services.interfaces.ILikeService;
import com.escuelita.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository repository;

    @Autowired
    ILikeService likeService;

    @Override
    public GetUserResponse get(Long id){
        return from(id);
    }

    @Override
    public BaseResponse list() {
        List<GetUserResponse> response = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("List of Clients have been obtained correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }


    //CREATE
    @Override
    public BaseResponse create(CreateUserRequest request) {
    Like like = likeService.create();
    User user = from(request,like);
    return BaseResponse.builder()
            .data(from(repository.save(user)))
            .message("User created correctly")
            .success(Boolean.TRUE)
            .httpStatus(HttpStatus.CREATED).build();
    }


    //UPDATE
    @Override
    public GetUserResponse update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("The user does not exist"));
        user = update(user, request);
        return from(user);
    }
    private User update(User user, UpdateUserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return repository.save(user);
    }
    private User from(CreateUserRequest request, Like like) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLike(like);
        return user;
    }

    //DELETE
    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }


    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setLikeResponse(from(user.getLike()));
        return response;
    }

    private GetUserResponse from (Long idUser){
        return repository.findById(idUser)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException("The user does not exist"));
    }

    private LikeResponse from(Like like){
    LikeResponse response= new LikeResponse();
    response.setId(like.getId());
    return response;
    }

}
