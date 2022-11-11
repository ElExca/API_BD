package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentRepository extends JpaRepository<Comment , Long> {

}
