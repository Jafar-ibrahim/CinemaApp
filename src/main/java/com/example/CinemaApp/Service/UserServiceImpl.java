package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.AppUser;

import com.example.CinemaApp.Entity.AppUserDto;
import com.example.CinemaApp.Entity.Ticket;
import com.example.CinemaApp.Entity.TicketDto;
import com.example.CinemaApp.Repository.UserRepo;
//import com.example.CinemaAPP.Security.AppUserDetail;
import com.example.CinemaApp.Security.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public AppUser findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public AppUser save(AppUser entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepo.save(entity);
    }


    public List<AppUser> findAll (){
        return userRepo.findAll();
    }

    public AppUser findById (Long id){
        return userRepo.findById(id).orElse(null);
    }

    public AppUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email =  authentication.getName();
            return findByEmail(email);
        } else {
            throw new InsufficientAuthenticationException("User is not authenticated");
        }
    }

    @Override
    public AppUserDto getUserProfile() {
        AppUser user = getCurrentUser();
        return new AppUserDto(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> appUser =	userRepo.findByEmail(username);

        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
        }
        return new AppUserDetail(appUser.get());
    }


}
