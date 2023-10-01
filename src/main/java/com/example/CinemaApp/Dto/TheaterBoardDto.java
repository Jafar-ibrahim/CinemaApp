package com.example.CinemaApp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterBoardDto {
    private Long TheaterId;
    private String currentMovie;
    private char[][] reservationsBoard;
}
