package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.request.CreateSongRequest;
import com.escuelita.demo.controllers.dtos.request.UpdateSongRequest;
import com.escuelita.demo.controllers.dtos.response.BaseResponse;
import com.escuelita.demo.controllers.dtos.response.ArtistResponse;
import com.escuelita.demo.controllers.dtos.response.GenreResponse;
import com.escuelita.demo.controllers.dtos.response.GetSongResponse;
import com.escuelita.demo.entities.Artist;
import com.escuelita.demo.entities.Genre;
import com.escuelita.demo.entities.Song;
import com.escuelita.demo.repositories.ISongRepository;
import com.escuelita.demo.services.interfaces.IArtistService;
import com.escuelita.demo.services.interfaces.IFileService;
import com.escuelita.demo.services.interfaces.IGenreService;
import com.escuelita.demo.services.interfaces.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private ISongRepository repository;
    @Autowired
    private IFileService fileService;

    @Autowired
    private IArtistService artistService;

    @Autowired
    private IGenreService genreService;

    @Override
    public GetSongResponse get(Long id) {
        return from(id);
    }

    @Override
    public BaseResponse list() {
        List<GetSongResponse> response= repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Songs have been found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateSongRequest request) {
        Song song = from (request);
        GetSongResponse response = from(repository.save(song));
        return BaseResponse.builder()
                .data(response)
                .message("Song created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();

    }

    @Override
    public BaseResponse upload(MultipartFile file){
        String songUrl= fileService.upload(file);
        return  BaseResponse.builder()
                .data(songUrl)
                .message("Song upload")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();

    }

    @Override
    public GetSongResponse update(Long id, UpdateSongRequest request) {
        Song song = repository.findById(id).orElseThrow(() -> new RuntimeException("The song does not exist"));
        song = update(song, request);
        return from(song);
    }

    private Song update(Song song, UpdateSongRequest request) {
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        return repository.save(song);
    }

    private Song from(CreateSongRequest request) {
        Song song = new Song();
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        song.setArtist(artistService.findById(request.getArtistId()));
        song.setGenre(genreService.findById(request.getGenreId()));
        song.setDate(request.getDate());
        song.setSongUrl(request.getSongUrl());
        return song;
    }

    private GetSongResponse from(Song song) {
        GetSongResponse response = new GetSongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setDuration(song.getDuration());
        response.setDate(song.getDate());
        response.setSongUrl(song.getSongUrl());
        response.setArtist(from(song.getArtist()));
        response.setGenre(from(song.getGenre()));
        return response;
    }

    private ArtistResponse from(Artist artist){
        ArtistResponse response= new ArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        response.setName(artist.getName());
        return response;
    }

    private GenreResponse from(Genre genre){
        GenreResponse response= new GenreResponse();
        response.setId(genre.getId());
        response.setName(genre.getName());
        return response;
    }
    private GetSongResponse from (Long idSong){
        return repository.findById(idSong)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException("The song does not exist"));
    }

    @Override
    public Song findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The song does not exist"));
    }
}
