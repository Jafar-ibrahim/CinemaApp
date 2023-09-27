package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.*;
import com.example.CinemaApp.Enum.SeatType;
import com.example.CinemaApp.Exception.InvalidRowOrColumnException;
import com.example.CinemaApp.Exception.TicketNotReservedException;
import com.example.CinemaApp.Exception.TicketReservedException;
import com.example.CinemaApp.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    TheaterService theaterService;


    public Ticket save(Ticket ticket){
        return ticketRepo.save(ticket);
    }

    public List<Ticket> findAll(){
        return ticketRepo.findAll();
    }

    public Ticket getById(Long id){
        return ticketRepo.findById(id).orElse(null);
    }


    public void initializeTickets(Theater theater){

        if(theater.getMovie() == null)
            throw new RuntimeException("Can't initialize tickets for a theater that has no movie to show !!");

        List<Ticket> tickets = new ArrayList<>();
        SeatType seatType = SeatType.PREMIUM;
        int rowLength = theater.getRowsNumber();

        for (int i = 1 ; i <= rowLength ; i++){
            if(i > 1)
                seatType = SeatType.CLASSIC;
            for (int j = 1 ; j <= rowLength; j++){
                tickets.add(save(new Ticket(seatType, theater ,i ,j)));
            }
        }
        theater.setTickets(tickets);
    }
    public Ticket findByRowNoAndColumnNoAndTheater_Id(int row , int col , Long theaterId) {

        return ticketRepo.findByRowNoAndColumnNoAndTheater_Id(row, col, theaterId)
                         .orElseThrow(() ->new EntityNotFoundException("No Ticket is found with the given row,col & theater !"));
    }

    public TicketSimpleDto getPrice(int row , int col, Long theaterId){

        Ticket ticket =  findByRowNoAndColumnNoAndTheater_Id(row, col, theaterId);
        return new TicketSimpleDto(ticket);
    }

    public List<Ticket> getAvailableTickets() {
        return ticketRepo.findAllByReservedFalse();
    }

    public double sumPriceByMovieName(String movieName){
        return ticketRepo.sumPriceByMovieName(movieName);
    }

    public Double sumPriceByMovieNameAndToday(String movieName){
        return ticketRepo.sumPriceByMovieNameAndToday(movieName);
    }

    public void verifyRowsAndCols(int row , int col , int maxRows , int maxCols){
        int minValue = 1;
        if (row > maxRows || row < minValue || col > maxCols|| col < minValue )
            throw new InvalidRowOrColumnException();
    }

    public List<TicketSimpleDto> getTheaterTickets(Theater theater) {

        return theater.getTickets()
                .stream()
                .filter(x->!x.isReserved())
                .map(TicketSimpleDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public TicketDto reserveTicket(Long theaterId,int row , int col ){

        Theater theater = theaterService.findById(theaterId);
        theaterService.checkIfFull(theater);

        verifyRowsAndCols(row,col,theater.getRowsNumber(),theater.getColumnsNumber());
        Ticket ticket = findByRowNoAndColumnNoAndTheater_Id(row,col , theaterId);

        if (ticket.isReserved())
            throw new TicketReservedException();

        ticket.setReserved(true);
        // adding the ticket to the user's list of tickets
        // temporary line ( need to implement security first)
        ticket.setUser(userService.findById(1L));
        // adding date of purchase
        ticket.setDateOfPurchase(LocalDateTime.now());
        save(ticket);

        // adding the ticket price to the movie revenue and increasing
        // number of purchased tickets
        Movie movie = theater.getMovie();
        movie.addToCurrentIncome(ticket.getPrice());
        movie.incrementNumberOfPurchasedTickets();
        movieService.save(movie);

        theater.increaseReservationCounter();
        theaterService.save(theater);


        System.out.println("reservation completed successfully !!!!!!");

        return new TicketDto(ticket);
    }

    @Transactional
    public void cancelTicketReservation(Long theaterId, int row , int col ){

        Theater theater = theaterService.findById(theaterId);

        verifyRowsAndCols(row,col,theater.getRowsNumber(),theater.getColumnsNumber());
        Ticket ticket = findByRowNoAndColumnNoAndTheater_Id(row,col , theaterId);

        if (!ticket.isReserved())
            throw new TicketNotReservedException();

        ticket.setReserved(false);
        theater.decreaseReservationCounter();

        // removing the ticket from the user's list of tickets
        // temporary line
        ticket.setUser(null);
        // removing date of purchase
        ticket.setDateOfPurchase(null);

        Movie movie = theater.getMovie();
        //removing the ticket price from the movie revenue and decreasing
        //number of purchased tickets
        movie.addToCurrentIncome(-1 * ticket.getPrice());
        movie.decrementNumberOfPurchasedTickets();

        movieService.save(movie);
        theaterService.save(theater);
        save(ticket);

        System.out.println("reservation deleted successfully !!!!!!");

    }


}
