package com.example.CinemaApp.Repository;

import com.example.CinemaApp.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByReservedFalse();

    Optional<Ticket> findById(Long id);

    Optional<Ticket> findByRowNoAndColumnNoAndTheater_Id(int row , int col , Long theaterId);

    @Query(value = "SELECT SUM(price) FROM tickets WHERE movie_name = :movieName", nativeQuery = true)
    Double sumPriceByMovieName(@Param("movieName") String movieName);

    @Query(value = "SELECT SUM(price) FROM tickets WHERE DATE(date_of_purchase) = CURRENT_DATE AND movie_name = :movieName", nativeQuery = true)
    Double sumPriceByMovieNameAndToday(@Param("movieName") String movieName);

    @Query(value = "SELECT COUNT(*) FROM tickets WHERE reserved = true AND movie_name = :movieName", nativeQuery = true)
    Long countReservedTickets(@Param("movieName") String movieName);

    @Query(value = "SELECT SUM(price) FROM tickets WHERE reserved = true AND movie_name = :movieName", nativeQuery = true)
    Double sumReservedTicketPrices(@Param("movieName") String movieName);


}
