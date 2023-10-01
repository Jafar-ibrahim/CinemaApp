package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Dto.TheaterBoardDto;
import com.example.CinemaApp.Dto.TheaterDto;
import com.example.CinemaApp.Service.TheaterService;
import com.example.CinemaApp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/theaters")
public class TheaterController {

    @Autowired
    TheaterService theaterService;
    @Autowired
    TicketService ticketService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    @ResponseBody
    public TheaterDto addTheater(){
        return theaterService.addTheater();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @ResponseBody
    public TheaterDto getTheater(@PathVariable Long id){
        return theaterService.getTheater(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    @ResponseBody
    public List<TheaterDto> getAllTheaters(){
        return theaterService.getAllTheaters();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public TheaterDto setTheater(@PathVariable Long id , String movieName , int rows , int columns){
        return theaterService.setTheater(id,movieName,rows,columns);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}/reservations-board")
    @ResponseBody     /* A --> Available   R--> Reserved */
    public TheaterBoardDto getReservationsBoard(@PathVariable Long id){
        return theaterService.getReservationsBoard(id);
    }
}
