package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.*;
import com.example.CinemaApp.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepo movieRepo;

    public Movie save(Movie movie){
        return movieRepo.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepo.findAll();
    }

    public Movie getMovieByName(String name){
        return movieRepo.findByName(name).
                orElseThrow(() ->new EntityNotFoundException("No Movie is found with the given name !"));
    }

    public Movie getMovieById(Long id ){
        return movieRepo.findById(id).
                orElseThrow(() ->new EntityNotFoundException("No Movie is found with the given id !"));
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        if (!movies.isEmpty()){
            return movies.stream().map(MovieDto::new).collect(Collectors.toList());
        }
        return null;
    }

    public MovieDto addMovie(Movie movie) {
        save(movie);
        return new MovieDto(movie);
    }

    public void deleteMovie(Long id) {
        if(movieRepo.existsById(id))
            movieRepo.deleteById(id);
    }

    public MovieDto getMovie(Long id) {
        Movie target = getMovieById(id);
        return new MovieDto(target);

    }

    public MovieDto setMovieShowtime(Long id, LocalDateTime showtime) {
        Movie target = getMovieById(id);

        target.setShowTime(showtime);
        save(target);

        return new MovieDto(target);
    }

}
