package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.Theater;
import com.example.CinemaApp.Entity.Theater;
import com.example.CinemaApp.Entity.TheaterDto;
import com.example.CinemaApp.Entity.TicketDto;
import com.example.CinemaApp.Service.CinemaService;
import com.example.CinemaApp.Service.TheaterService;
import com.example.CinemaApp.Service.TheaterService;
import com.example.CinemaApp.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cinema")
public class UserController {

    @Autowired
    CinemaService cinemaService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    TicketService ticketService;




    //************** Theater Controller ********************





}
