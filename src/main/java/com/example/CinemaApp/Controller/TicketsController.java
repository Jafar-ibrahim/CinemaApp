package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.TicketDto;
import com.example.CinemaApp.Entity.TicketSimpleDto;
import com.example.CinemaApp.Service.TheaterService;
import com.example.CinemaApp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/theaters/{theaterId}/tickets")
public class TicketsController {

    @Autowired
    TicketService ticketService;
    @Autowired
    TheaterService theaterService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @ResponseBody
    public List<TicketSimpleDto> getTheaterTickets(@PathVariable Long theaterId){
        return ticketService.getTheaterTickets(theaterService.findById(theaterId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public void initializeTickets(@PathVariable Long theaterId){
        ticketService.initializeTickets(theaterService.findById(theaterId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping
    public void DeleteTickets(@PathVariable Long theaterId){
        ticketService.deleteTickets(theaterService.findById(theaterId));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/reservation")
    @ResponseBody
    public TicketDto AdminReserveTicket(@PathVariable Long theaterId,int row , int col , Long userId){
        return ticketService.adminReserveTicket(theaterId, row, col,userId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/reservation")
    @ResponseBody
    public void cancelTicketReservation(@PathVariable Long theaterId,int row , int col ){
         ticketService.adminCancelTicketReservation(theaterId, row, col);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/price")
    @ResponseBody
    public TicketSimpleDto getTicketPrice(int row, int col , @PathVariable Long theaterId){
        return ticketService.getPrice(row, col, theaterId);
    }

}
