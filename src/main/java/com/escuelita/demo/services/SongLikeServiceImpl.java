package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.request.CreateSongLikeRequest;
import com.escuelita.demo.controllers.dtos.response.*;
import com.escuelita.demo.entities.Artist;
import com.escuelita.demo.entities.Genre;
import com.escuelita.demo.entities.Like;
import com.escuelita.demo.entities.Song;
import com.escuelita.demo.entities.pivots.SongLike;
import com.escuelita.demo.entities.projections.SongProjection;
import com.escuelita.demo.repositories.ISongLikeRepository;
import com.escuelita.demo.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongLikeServiceImpl implements ISongLikeService {

    @Autowired
    private ISongLikeRepository repository;

    @Autowired
    private ISongService songService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IArtistService artistService;

    @Autowired
    private IGenreService genreService;

    @Override
    public BaseResponse create(CreateSongLikeRequest request) {
        SongLike songLike = from(request);

        GetSongLikeResponse response = from(repository.save(songLike));
        return BaseResponse.builder()
                .data(response).message("Like and song created ")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse listAllSongsByLikeId(Long likeId) {
        List<SongProjection> songs = repository.listAllSongsByLikeId(likeId);
        List<GetSongResponse> response = songs.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Songs list by Like").
                success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    private GetSongLikeResponse from(SongLike songLike) {
        GetSongLikeResponse response = new GetSongLikeResponse();
        response.setId(songLike.getId());
        response.setLike(from(songLike.getLike()));
        response.setSong(from(songLike.getSong()));
        return response;

    }


    private GetSongResponse from(SongProjection song) {
        GetSongResponse response = new GetSongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setDate(song.getDate());
        response.setDuration(song.getDuration());
        response.setSongUrl(song.getSong_Url());
        response.setArtist(from(artistService.findById(song.getArtist_Id())));
        response.setGenre(from(genreService.findById(song.getGenre_Id())));
        return response;
    }


    private SongLike from(CreateSongLikeRequest request) {
        SongLike songLike = new SongLike();
        songLike.setLike(likeService.findById(request.getIdLike()));
        songLike.setSong(songService.findById(request.getIdSong()));
        return songLike;
    }

    private LikeResponse from(Like like) {
        LikeResponse response = new LikeResponse();
        response.setId(like.getId());
        return response;
    }

    private SongResponse from(Song song) {
        SongResponse response = new SongResponse();
        response.setId(song.getId());
        response.setName(song.getName());
        response.setDuration(song.getDuration());
        response.setDate(song.getDate());
        response.setSongUrl(song.getSongUrl());
        response.setArtist(from(song.getArtist()));
        response.setGenre(from(song.getGenre()));
        return response;
    }

    private ArtistResponse from(Artist artist) {
        ArtistResponse response = new ArtistResponse();
        response.setId(artist.getId());
        response.setName(artist.getName());
        return response;
    }

    private GenreResponse from(Genre genre) {
        GenreResponse response = new GenreResponse();
        response.setId(genre.getId());
        response.setName(genre.getName());
        return response;
    }

    @Override
    public void deleteSongsByIdLike(Long likeId) {
        repository.deleteSongsByIdLike(likeId);
    }
}
