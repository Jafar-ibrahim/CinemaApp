package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.*;
import com.example.CinemaApp.Enum.SeatType;
import com.example.CinemaApp.Exception.*;
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

    public void verifyBeforeInitialization(Theater theater){
        if(theater.getMovie() == null)
            throw new TheaterHasNoMovieException();
        if (theater.getTickets()!=null && !theater.getTickets().isEmpty())
            throw new TicketsAlreadyInitializedException();
    }

    public void initializeTickets(Theater theater){

        verifyBeforeInitialization(theater);

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

    public void deleteTickets(Theater theater){
        List<Ticket> tickets = theater.getTickets();
        ticketRepo.deleteAll(tickets);
    }
    public Ticket findByRowNoAndColumnNoAndTheater_Id(int row , int col , Long theaterId) {

        return ticketRepo.findByRowNoAndColumnNoAndTheater_Id(row, col, theaterId)
                         .orElseThrow(() ->new EntityNotFoundException("No Ticket is found with the given row,col & theater !"));
    }

    public TicketSimpleDto getPrice(int row , int col, Long theaterId){

        Ticket ticket =  findByRowNoAndColumnNoAndTheater_Id(row, col, theaterId);
        return new TicketSimpleDto(ticket);
    }

    public Double sumPriceByMovieName(String movieName){
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
    public TicketDto adminReserveTicket(Long theaterId,int row , int col ,Long userId){
        AppUser user = userService.findById(userId);
        return reserveTicket(theaterId,row,col,user);
    }

    @Transactional
    public TicketDto userReserveTicket(Long theaterId,int row , int col) {
        AppUser user = userService.getCurrentUser();
        return reserveTicket(theaterId,row,col,user);
    }

    @Transactional
    public TicketDto reserveTicket(Long theaterId,int row , int col , AppUser user){

        Theater theater = theaterService.findById(theaterId);
        theaterService.checkIfFull(theater);

        verifyRowsAndCols(row,col,theater.getRowsNumber(),theater.getColumnsNumber());
        Ticket ticket = findByRowNoAndColumnNoAndTheater_Id(row,col , theaterId);

        if (ticket.isReserved())
            throw new TicketReservedException();

        ticket.setReserved(true);

        // adding the ticket to the user's list of tickets
        ticket.setUser(user);

        // adding date of purchase
        ticket.setDateOfPurchase(LocalDateTime.now());

        // adding the ticket price to the movie revenue and increasing
        // number of purchased tickets
        Movie movie = theater.getMovie();
        movie.addToCurrentIncome(ticket.getPrice());
        movie.incrementNumberOfPurchasedTickets();

        theater.increaseReservationCounter();
        theaterService.save(theater);

        return new TicketDto(ticket);
    }

    @Transactional
    public void adminCancelTicketReservation(Long theaterId , int row , int col){

        Theater theater = theaterService.findById(theaterId);
        verifyRowsAndCols(row,col,theater.getRowsNumber(),theater.getColumnsNumber());
        Ticket ticket = findByRowNoAndColumnNoAndTheater_Id(row,col , theaterId);

        cancelTicketReservation(theater,ticket);
    }

    @Transactional
    public void userCancelTicketReservation(Long ticketId){

        Ticket ticket = getById(ticketId);
        Theater theater = ticket.getTheater();

        cancelTicketReservation(theater,ticket);
    }

    @Transactional
    public void cancelTicketReservation(Theater theater , Ticket ticket){

        //Ticket ticket = getById(ticketId);
        //Theater theater = ticket.getTheater();

        if (!ticket.isReserved())
            throw new TicketNotReservedException();

        ticket.setReserved(false);
        theater.decreaseReservationCounter();

        // removing the ticket from the user's list of tickets
        ticket.setUser(null);
        // removing date of purchase
        ticket.setDateOfPurchase(null);


        // removing the ticket price from the movie revenue and decreasing
        // number of purchased tickets
        Movie movie = theater.getMovie();
        movie.addToCurrentIncome(-1 * ticket.getPrice());
        movie.decrementNumberOfPurchasedTickets();

        theaterService.save(theater);

    }

    public Long countReservedTickets(String movieName){
        return ticketRepo.countReservedTickets(movieName);
    }

    public Double sumReservedTicketPrices(String movieName){
        return ticketRepo.sumReservedTicketPrices(movieName);
    }



}
