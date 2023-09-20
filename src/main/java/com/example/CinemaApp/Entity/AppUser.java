package com.example.CinemaApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    private Long id;

    private String email;

    private String password;

    List<TicketDto> tickets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @OrderColumn(name = "id")
    private Set<Role> roles = new HashSet<>();

    private boolean isEnabled;

    private boolean isCredentialsNonExpired;

    private boolean isAccountNonLocked;

    private boolean isAccountNonExpired;

    public AppUser(Long id) {
        this.id = id;
    }
}
