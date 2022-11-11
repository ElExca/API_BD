package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.pivots.SongLike;
import com.escuelita.demo.entities.projections.SongProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ISongLikeRepository extends JpaRepository<SongLike, Long> {

    @Query(value = "select songs.* from songs_likes " +
            "inner join likes on songs_likes.like_id = likes.id " +
            "inner join songs on songs_likes.song_id = songs.id " +
            "where songs_likes.like_id = :likeId",nativeQuery = true)
    List<SongProjection> listAllSongsByLikeId(Long likeId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM songs_likes WHERE like_id= :likeId", nativeQuery= true)
    void deleteSongsByIdLike(Long likeId);
}
