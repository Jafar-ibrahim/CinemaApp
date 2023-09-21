package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Cinema;
import com.example.CinemaApp.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaRepo extends JpaRepository<Cinema,Long> {

}
