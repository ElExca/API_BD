package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Artist;
import com.escuelita.demo.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "SELECT * FROM genres WHERE name=:name", nativeQuery = true)
    Optional<Genre> findByName(String name);

}


