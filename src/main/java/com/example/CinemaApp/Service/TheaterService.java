package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.*;
import com.example.CinemaApp.Exception.TheaterIsFullException;
import com.example.CinemaApp.Repository.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    TheaterRepo theaterRepo;
    @Autowired
    MovieService movieService;





    public Theater save(Theater theater){
        return theaterRepo.save(theater);
    }


    public List<Theater> findAll(){
        return theaterRepo.findAll();
    }
    public Theater findById(Long id){
        return theaterRepo.findById(id).
                orElseThrow(() ->new EntityNotFoundException("No Theater is found with the given id !"));
    }

    public void checkIfFull(Theater theater){
        if (theater.getReservationCounter() >=
                theater.getRowsNumber()* theater.getColumnsNumber())
            throw new TheaterIsFullException();
    }

    public TheaterDto addTheater() {
      Theater theater = save(new Theater());
      return new TheaterDto(theater.getId());
    }


    public TheaterDto getTheater(Long id) {
        Theater target = findById(id);
        return getAppropriateTheaterDto(target);
    }

    public List<TheaterDto> getAllTheaters() {
        List<Theater> theaters = theaterRepo.findAll();
        if (!theaters.isEmpty()){
            return theaters.stream()
                           .map(this::getAppropriateTheaterDto)
                           .collect(Collectors.toList());
        }
        return null;
    }

    public TheaterDto getAppropriateTheaterDto(Theater theater){
        return theater.getMovie()==null?
                new TheaterDto(theater.getId()) :
                new TheaterDto(theater);
    }
    

    public void deleteTheater(Long id) {
        Theater theater = findById(id);
        theaterRepo.delete(theater);
    }


    public TheaterDto setTheater(Long theaterId , String movieName , int rows, int columns) {
        Theater theater = findById(theaterId);
        Movie movie = movieService.getMovieByName(movieName);

        theater.setRowsNumber(rows);
        theater.setColumnsNumber(columns);
        theater.setMovie(movie);

        save(theater);
        return getAppropriateTheaterDto(theater);
    }

    public TheaterBoardDto getReservationsBoard(Long id) {
        Theater theater = findById(id);
        List<Ticket> tickets = theater.getTickets();
        int rowLength = theater.getRowsNumber();

        // I multiplied the columns number by 2 to space the characters
        // ( for readability ) , and added an extra numbering row and column
        char[][] ReservationsBoard = new char[rowLength+1][2*rowLength+1];

        // initialize empty array
        for (int i = 0; i < ReservationsBoard.length; i++) {
            for (int j = 0; j < ReservationsBoard[0].length; j++) {
                ReservationsBoard[i][j] = ' ';
            }
        }

        // filling the first row and column with rows/cols numbers
        for (int i = 2 , j=1  ; i < ReservationsBoard[0].length ; i+=2 , j++){

            ReservationsBoard[0][i] = Character.forDigit(j, 10);
            ReservationsBoard[j][0] = Character.forDigit(j, 10);
        }
        char available = 'A';
        char reserved = 'R';

        // printing a character every 2 columns (to make spaces which
        // makes it more readable)
        for (int i = 1  ; i < ReservationsBoard.length ; i++){
            for (int j = 1  ; j < ReservationsBoard[0].length ; j++){

                if(j%2 != 0) {
                    ReservationsBoard[i][j] = ' ';
                    continue;
                }
                //to generate the index that corresponds to row,col
                int arrayIndex = ((i-1)*rowLength) + (j/2 -1);

                if(tickets.get(arrayIndex).isReserved())
                    ReservationsBoard[i][j] = reserved;
                else
                    ReservationsBoard[i][j] = available;
            }
        }

        return new TheaterBoardDto(theater.getId(),
                                theater.getMovie().getName(),
                                ReservationsBoard);

    }


}
