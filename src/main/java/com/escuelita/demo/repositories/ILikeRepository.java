package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikeRepository extends JpaRepository<Like, Long> {
}
