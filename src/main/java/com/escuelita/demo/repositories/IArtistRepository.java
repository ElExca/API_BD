package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Long> {
    @Query(value = "SELECT * FROM artists WHERE name=:name", nativeQuery = true)
    Optional<Artist> findByName(String name);
}
