package com.example.CinemaApp.Dto;

import com.example.CinemaApp.Entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String name;

    private String description;

    private double price;

    private LocalDateTime showTime;

    public MovieDto(Movie movie) {
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.price = movie.getPrice();
        this.showTime = movie.getShowTime();
    }
}
