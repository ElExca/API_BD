package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetLikeResponse;
import com.escuelita.demo.entities.Like;
import com.escuelita.demo.repositories.ILikeRepository;
import com.escuelita.demo.services.interfaces.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl  implements ILikeService {
    @Autowired
    private ILikeRepository repository;

    @Override
    public Like create() {
        Like like = new Like();
        return repository.save(like);
    }

    @Override
    public BaseResponse get(Long id) {
        GetLikeResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("Like founded")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private GetLikeResponse from(Like like) {
        GetLikeResponse response = new GetLikeResponse();
        response.setId(like.getId());
        return response;
    }

    @Override
    public BaseResponse list() {
        List<GetLikeResponse> response=repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Likes founded")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private GetLikeResponse from(Long id) {
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public Like findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }
}
