package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.AppUser;
import com.example.CinemaApp.Entity.AppUserDto;
import com.example.CinemaApp.Entity.TicketDto;
import org.springframework.security.core.userdetails.UserDetailsService;

//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    AppUser findByEmail(String email);
     AppUser findById (Long id);
    AppUser save(AppUser user);
    List<AppUser> findAll();
     AppUser getCurrentUser();

    AppUserDto getUserProfile();

    boolean existsByEmail(String email);
}
