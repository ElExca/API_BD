package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.request.CreateArtistRequest;
import com.escuelita.demo.controllers.dtos.request.CreateGenreRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.GetArtistResponse;
import com.escuelita.demo.controllers.dtos.response.GetGenreResponse;
import com.escuelita.demo.entities.Artist;
import com.escuelita.demo.entities.Genre;
import com.escuelita.demo.repositories.IGenreRepository;
import com.escuelita.demo.repositories.ISongRepository;
import com.escuelita.demo.services.interfaces.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GenreServiceImpl implements IGenreService {
    @Autowired
    private IGenreRepository repository;

    @Override
    public List<GetGenreResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetGenreResponse get(Long id){
        return from(id);
    }


    private GetGenreResponse from (Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException("The artist does not exist"));
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateGenreRequest request) {
        Genre genre = from(request);
        GetGenreResponse response = from(repository.save(genre));
        return BaseResponse.builder()
                .data(response)
                .message("Genre created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    private Genre from(CreateGenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());
        return genre;
    }

    private GetGenreResponse from(Genre genre) {
        GetGenreResponse response = new GetGenreResponse();
        response.setId(genre.getId());
        response.setName(genre.getName());
        return response;
    }

    @Override
    public Genre findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The genre does not exist"));
    }
}
