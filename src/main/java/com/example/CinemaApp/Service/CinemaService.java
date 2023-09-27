package com.example.CinemaApp.Service;

import com.example.CinemaApp.Entity.Cinema;
import com.example.CinemaApp.Repository.CinemaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    CinemaRepo cinemaRepo ;

    public Cinema save(Cinema cinema){
        return cinemaRepo.save(cinema);
    }

    public List<Cinema> findAll(){
        return cinemaRepo.findAll();
    }

    public Cinema getById(Long id){
        return cinemaRepo.findById(id).orElse(null);
    }
}
