package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Entity.DailySalesReportDto;
import com.example.CinemaApp.Entity.MovieStatisticsDto;
import com.example.CinemaApp.Service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema/movies/{MovieId}")
public class ReportingController {

    @Autowired
    ReportingService reportingService;

    @GetMapping("/statistics")
    @ResponseBody
    public MovieStatisticsDto getStatistics(@PathVariable Long MovieId){
        return reportingService.getStatistics(MovieId);
    }

    @GetMapping("/daily-sales")
    @ResponseBody
    public DailySalesReportDto getDailySalesReport(@PathVariable Long MovieId){
        return reportingService.getDailySalesReport(MovieId);
    }
}
