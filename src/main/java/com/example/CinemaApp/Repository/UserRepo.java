package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
}
