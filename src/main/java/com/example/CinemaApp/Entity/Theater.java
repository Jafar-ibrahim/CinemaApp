package com.example.CinemaApp.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "theaters_info",schema = "cinema_app")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movie_name" , referencedColumnName = "name")
    private Movie movie;

    @OneToMany(mappedBy = "theater" , cascade = CascadeType.MERGE)
    private List<Ticket> tickets;

    private int rowsNumber = 8;

    private int columnsNumber = 8;

    private int reservationCounter = 0;

    public void increaseReservationCounter(){
        this.reservationCounter++;
    }
    public void decreaseReservationCounter(){
        this.reservationCounter--;
    }

    public Theater(Movie movie) {
        this.movie = movie;
    }

    public Theater(Movie movie, int rowsNumber, int columnsNumber) {
        this.movie = movie;
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
    }
}
