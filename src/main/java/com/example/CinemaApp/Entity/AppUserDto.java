package com.example.CinemaApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private String name;

    private String email;

    List<TicketDto> tickets;

    public AppUserDto(AppUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.tickets= user.getTickets()
                .stream()
                .map(TicketDto::new)
                .collect(Collectors.toList());
    }
}
