package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Movie;
import com.example.CinemaApp.Entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepo extends JpaRepository<Theater,Long> {
    Optional<Theater> findById(Long id);

}
