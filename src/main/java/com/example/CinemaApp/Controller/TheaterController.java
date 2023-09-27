package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.TheaterBoardDto;
import com.example.CinemaApp.Entity.TheaterDto;
import com.example.CinemaApp.Entity.TicketSimpleDto;
import com.example.CinemaApp.Service.TheaterService;
import com.example.CinemaApp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/theaters")
public class TheaterController {

    @Autowired
    TheaterService theaterService;
    @Autowired
    TicketService ticketService;

    @PostMapping
    @ResponseBody
    public TheaterDto addTheater(){
        return theaterService.addTheater();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TheaterDto getTheater(@PathVariable Long id){
        return theaterService.getTheater(id);
    }

    @GetMapping
    @ResponseBody
    public List<TheaterDto> getAllTheaters(){
        return theaterService.getAllTheaters();
    }

    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
    }

    @PutMapping("/{id}")
    public TheaterDto setTheater(@PathVariable Long id , String movieName , int rows , int columns){
        return theaterService.setTheater(id,movieName,rows,columns);
    }

    @GetMapping("/{id}/reservations-board")
    @ResponseBody     /* A --> Available   R--> Reserved */
    public TheaterBoardDto getReservationsBoard(@PathVariable Long id){
        return theaterService.getReservationsBoard(id);
    }
}
