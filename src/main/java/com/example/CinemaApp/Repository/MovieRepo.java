package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> findById(Long id);
    Optional<Movie> findByName(String name);
}
