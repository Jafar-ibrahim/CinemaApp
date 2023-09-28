package com.example.CinemaApp.Config;

import com.example.CinemaApp.Entity.*;
import com.example.CinemaApp.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final TheaterService theaterService;
    private final MovieService movieService;
    private final TicketService ticketService;

    @Override
    public void run(String... args) throws Exception {


        if (roleService.findAll().isEmpty()) {
            roleService.save(new Role(null, "admin"));
            roleService.save(new Role(null, "user"));
            roleService.save(new Role(null, "employee"));
        }

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.findByName("admin"));

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findByName("user"));

        Set<Role>  empRoles = new HashSet<>();
        empRoles.add(roleService.findByName("employee"));


        if (userService.findAll().isEmpty()) {
            userService.save(new AppUser(null,"jafar@gmail.com","123","Jafar",null,adminRoles,true,true,true,true));
        }

        if(movieService.findAll().isEmpty()){
            movieService.save(new Movie("Interstellar",
                    "Something about space and time travel",
                    12.0,
                    LocalDateTime.now().plusHours(48)));
            movieService.save(new Movie("Demon Slayer",
                    "A  kid who want to return his demon sister to a human",
                    7.0,
                    LocalDateTime.now().plusHours(72)));
        }

        List<Theater> theaters = new ArrayList<>();

        if (theaterService.findAll().isEmpty()){
            theaters.add(theaterService.save(new Theater(movieService.getMovieByName("Interstellar"),8,8)));
            theaters.add(theaterService.save(new Theater(movieService.getMovieByName("Demon Slayer"),8,8)));
        }
        if(ticketService.findAll().isEmpty()){
            theaters.forEach(x->{
                if (x.getMovie() !=null)
                    ticketService.initializeTickets(x);});
        }

        //theaterService.unreserveTicket(2L,3,3);
        //theaterService.reserveTicket(2L,4,3);

        //Ticket test = ticketService.findByRowNoAndColumnNoAndTheater_Id(1,2,2L);

        //System.out.println(test);
        //System.out.println(theaterService.findById(2L));

        //System.out.println(userService.findById(1L));




    }
}
