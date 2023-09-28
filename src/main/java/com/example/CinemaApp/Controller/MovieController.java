package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.DailySalesReportDto;
import com.example.CinemaApp.Entity.Movie;
import com.example.CinemaApp.Entity.MovieDto;
import com.example.CinemaApp.Entity.MovieStatisticsDto;
import com.example.CinemaApp.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cinema/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public MovieDto addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MovieDto getMovie(@PathVariable Long id){
        return movieService.getMovie(id);
    }

    @GetMapping
    @ResponseBody
    public List<MovieDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
    }

    @PutMapping("/{id}")
    public MovieDto setMovieShowtime(@PathVariable Long id,
                                     LocalDateTime showTime){
        return movieService.setMovieShowtime(id,showTime);
    }


}
