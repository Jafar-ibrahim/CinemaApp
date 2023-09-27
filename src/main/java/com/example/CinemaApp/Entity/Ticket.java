package com.example.CinemaApp.Entity;

import com.example.CinemaApp.Enum.SeatType;
import com.example.CinemaApp.Service.TicketIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets",schema = "cinema_app")
public class Ticket {

    @Id
    @GeneratedValue(generator = TicketIdGenerator.generatorName)
    @GenericGenerator(name = TicketIdGenerator.generatorName
            ,strategy = "com.example.CinemaApp.Service.TicketIdGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private AppUser user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="theater_id",referencedColumnName = "id")
    private Theater theater;

    private String movieName;

    @Column(nullable = false)
    private int rowNo;

    @Column(nullable = false)
    private int columnNo;

    @Column(nullable = false)
    private double price ;

    private boolean reserved;

    private LocalDateTime dateOfPurchase;

    public Ticket(SeatType seatType, int rowNo, int columnNo, double price) {
        this.seatType = seatType;
        this.rowNo = rowNo;
        this.columnNo = columnNo;
        this.price = price;
    }

    public Ticket(SeatType seatType, Theater theater, int rowNo, int columnNo) {
        this.seatType = seatType;
        this.theater = theater;
        this.rowNo = rowNo;
        this.columnNo = columnNo;
        this.movieName = theater.getMovie().getName();
        this.price = calcPrice();
    }

    public double calcPrice() {
        return theater.getMovie().getPrice() + seatType.getPrice();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatType=" + seatType +
                ", theater=" + theater +
                ", rowNo=" + rowNo +
                ", columnNo=" + columnNo +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}
