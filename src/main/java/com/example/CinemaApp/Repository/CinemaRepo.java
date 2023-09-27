package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Cinema;
import com.example.CinemaApp.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema,Long> {

    Optional<Cinema> findById(Long id);
}
