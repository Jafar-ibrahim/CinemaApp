package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.Movie;
import com.example.CinemaApp.Dto.MovieDto;
import com.example.CinemaApp.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cinema/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public MovieDto addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    @ResponseBody
    public MovieDto getMovie(@PathVariable Long id){
        return movieService.getMovie(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @ResponseBody
    public List<MovieDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public MovieDto setMovieShowtime(@PathVariable Long id,
                                     LocalDateTime showTime){
        return movieService.setMovieShowtime(id,showTime);
    }


}
