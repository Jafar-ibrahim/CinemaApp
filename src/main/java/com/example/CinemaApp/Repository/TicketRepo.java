package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByReservedFalse();

    Optional<Ticket> findById(Long id);
}
