package com.example.CinemaApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theaters_info",schema = "cinema_app")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_name" , referencedColumnName = "name")
    private Movie movie;

    @OneToMany(mappedBy = "theater")
    private List<Ticket> tickets;

    private boolean isFull;
}
