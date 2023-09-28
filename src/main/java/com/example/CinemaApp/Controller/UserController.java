package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.AppUserDto;
import com.example.CinemaApp.Entity.TicketDto;
import com.example.CinemaApp.Service.TicketService;
import com.example.CinemaApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;

    @GetMapping("/profile")
    @ResponseBody
    AppUserDto getUserTickets(){
        return userService.getUserProfile();
    }


}
