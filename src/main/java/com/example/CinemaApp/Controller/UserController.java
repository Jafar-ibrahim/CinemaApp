package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Dto.AppUserDto;
import com.example.CinemaApp.Dto.TicketDto;
import com.example.CinemaApp.Service.TicketService;
import com.example.CinemaApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
@RestController
@RequestMapping("/cinema/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;

    @GetMapping("/profile")
    @ResponseBody
    AppUserDto getUserProfile(){
        return userService.getUserProfile();
    }

    @GetMapping("/tickets")
    @ResponseBody
    List<TicketDto> getUserTickets(){
        return userService.getUserTickets();
    }

    @PatchMapping("/tickets/reservation")
    @ResponseBody
    public TicketDto ReserveTicket(Long theaterId,int row , int col){
        return ticketService.userReserveTicket(theaterId, row, col);
    }

    @DeleteMapping("/tickets/reservation")
    @ResponseBody
    public void cancelTicketReservation(Long ticketId){
        ticketService.userCancelTicketReservation(ticketId);
    }


}
