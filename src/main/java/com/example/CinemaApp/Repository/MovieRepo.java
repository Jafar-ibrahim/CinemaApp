package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> findById(Long id);
    Optional<Movie> findByName(String name);
}
