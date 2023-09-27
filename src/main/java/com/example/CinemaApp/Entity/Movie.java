package com.example.CinemaApp.Entity;


import com.example.CinemaApp.Exception.PastDateReservationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies",schema = "cinema_app")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    //Just to not have any decimals in the seconds
    @Column(columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime showTime;

    // the relationship is One-To-Many to give the possibility of showing
    // the same movie in multiple theaters (in case of a popular movie ^_^ )
    @OneToMany(mappedBy = "movie")
    private Set<Theater> theaters = new HashSet<>();

    private int numberOfPurchasedTickets = 0 ;

    private double currentIncome = 0;

    public void addToCurrentIncome(double price){
        this.setCurrentIncome(currentIncome+price);
    }
    public void incrementNumberOfPurchasedTickets(){
        numberOfPurchasedTickets++;
    }
    public void decrementNumberOfPurchasedTickets(){
        numberOfPurchasedTickets--;
    }

    public Movie(String name, String description, double price, LocalDateTime showTime) {

        if(showTime.isBefore(LocalDateTime.now()))
            throw new PastDateReservationException();
        this.name = name;
        this.description = description;
        this.price = price;
        this.showTime = showTime;
    }
}
