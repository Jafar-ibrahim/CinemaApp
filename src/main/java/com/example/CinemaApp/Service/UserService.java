package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.AppUser;

//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService /*extends UserDetailsService*/ {

   // List<TicketDto> getTickets(String email) ;
    AppUser findByEmail(String email);
     AppUser findById (Long id);
    AppUser save(AppUser user);
    List<AppUser> findAll();

}
