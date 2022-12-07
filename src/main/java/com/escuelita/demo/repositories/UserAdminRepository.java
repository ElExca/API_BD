package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAdminRepository extends JpaRepository<UserAdmin, Integer> {

    Optional<UserAdmin> findOneByEmail(String email);
}
