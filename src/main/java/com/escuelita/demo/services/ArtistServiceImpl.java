package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.request.CreateArtistRequest;
import com.escuelita.demo.controllers.dtos.request.CreateUserRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateArtistRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateUserRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetArtistResponse;
import com.escuelita.demo.controllers.dtos.response.GetUserResponse;
import com.escuelita.demo.entities.Artist;
import com.escuelita.demo.entities.Like;
import com.escuelita.demo.entities.User;
import com.escuelita.demo.repositories.IArtistRepository;
import com.escuelita.demo.services.interfaces.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements IArtistService {
    @Autowired
    IArtistRepository repository;

    @Override
    public GetArtistResponse get(Long id){
        return from(id);
    }

    @Override
    public List<GetArtistResponse> list(){
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public Artist findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The artist does not exist"));
    }

    //CREATE
    @Override
    public BaseResponse create(CreateArtistRequest request) {
        Artist artist = from(request);
        GetArtistResponse response=from(repository.save(artist));
        return BaseResponse.builder()
                .data(response)
                .message("Artist created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }


    //UPDATE
    @Override
    public GetArtistResponse update(Long id, UpdateArtistRequest request) {
        Artist artist = repository.findById(id).orElseThrow(() -> new RuntimeException("The artist does not exist"));
        artist = update(artist, request);
        return from(artist);
    }

    private Artist update(Artist artist, UpdateArtistRequest request) {
        artist.setName(request.getName());
        return repository.save(artist);
    }
    private Artist from(CreateArtistRequest request) {
        Artist artist = new Artist();
        artist.setName(request.getName());
        return artist;
    }

    //DELETE
    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }


    private GetArtistResponse from(Artist artist) {
        GetArtistResponse response = new GetArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        return response;
    }

    private GetArtistResponse from (Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException("The artist does not exist"));
    }


}
