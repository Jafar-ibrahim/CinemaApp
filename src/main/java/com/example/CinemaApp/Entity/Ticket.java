package com.example.CinemaApp.Entity;

import com.example.CinemaApp.Enum.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets",schema = "cinema_app")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    AppUser user;

    @Column(nullable = false)
    private SeatType seatType;

    @ManyToOne()
    @JoinColumn(name="theater_id", nullable=false ,referencedColumnName = "id")
    private Theater theater;

    @Column(nullable = false)
    private int rowNo;

    @Column(nullable = false)
    private int columnNo;

    @Column(nullable = false)
    private double price;

    private boolean isReserved;

}
