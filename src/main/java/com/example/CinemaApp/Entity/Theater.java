package com.example.CinemaApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Theater {

    private Long id;

    private Movie movie;

    private List<Ticket> tickets;

    private boolean isFull;
}
