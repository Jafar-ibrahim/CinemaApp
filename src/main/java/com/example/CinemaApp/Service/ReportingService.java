package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.DailySalesReportDto;
import com.example.CinemaApp.Entity.Movie;
import com.example.CinemaApp.Entity.MovieStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportingService {

    @Autowired
    MovieService movieService;
    @Autowired
    TicketService ticketService;

    public MovieStatisticsDto getStatistics(Long MovieId){
        Movie movie = movieService.getMovieById(MovieId);

        Long numberOfPurchasedTickets = ticketService.countReservedTickets(movie.getName());

        // Calculate the current income for the movie
        Double currentIncome = Optional
                .ofNullable(ticketService.sumReservedTicketPrices(movie.getName()))
                .orElse(0.0);

        // Calculate the maximum possible income for the movie
        Double maxIncome = Optional
                .ofNullable(ticketService.sumPriceByMovieName(movie.getName()))
                .orElse(0.0);

        return new MovieStatisticsDto(numberOfPurchasedTickets,currentIncome,maxIncome);
    }

    public DailySalesReportDto getDailySalesReport(Long id){
        String movieName = movieService.getMovieById(id).getName();

        // Calculate the total sales for the movie for the current day
        Double totalSales = Optional
                .ofNullable(ticketService.sumPriceByMovieNameAndToday(movieName))
                .orElse(0.0);

        return new DailySalesReportDto(movieName,totalSales);
    }
}
