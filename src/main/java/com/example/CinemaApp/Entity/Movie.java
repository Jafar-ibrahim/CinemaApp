package com.example.CinemaApp.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;

    private String name;

    private double price;

    private Date showDate;


}
