package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.TicketDto;
import com.example.CinemaApp.Entity.TicketSimpleDto;
import com.example.CinemaApp.Service.TheaterService;
import com.example.CinemaApp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/theaters/{theaterId}/tickets")
public class TicketsController {

    @Autowired
    TicketService ticketService;
    @Autowired
    TheaterService theaterService;

    @GetMapping
    @ResponseBody
    public List<TicketSimpleDto> getTheaterTickets(@PathVariable Long theaterId){
        return ticketService.getTheaterTickets(theaterService.findById(theaterId));
    }
    @PatchMapping("/reservation")
    @ResponseBody
    public TicketDto reserveTicket(@PathVariable Long theaterId,int row , int col ){
        return ticketService.reserveTicket(theaterId, row, col);
    }

    @DeleteMapping("/reservation")
    @ResponseBody
    public void cancelTicketReservation(@PathVariable Long theaterId,int row , int col ){
         ticketService.cancelTicketReservation(theaterId, row, col);
    }

    @PutMapping("/init")
    public void initializeTicket(@PathVariable Long theaterId){
        ticketService.initializeTickets(theaterService.findById(theaterId));
    }

    @GetMapping("/price")
    @ResponseBody
    public TicketSimpleDto getTicketPrice(int row, int col , @PathVariable Long theaterId){
        return ticketService.getPrice(row, col, theaterId);
    }





}
