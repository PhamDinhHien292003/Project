package com.example.vgc_project.service.ServiceImp;

import com.example.vgc_project.DTO.CinemaDTO;

import java.util.List;

public interface CinemaImp {
    List<CinemaDTO> getAllCinema();
    boolean addCinema(String name , int seat );
}
