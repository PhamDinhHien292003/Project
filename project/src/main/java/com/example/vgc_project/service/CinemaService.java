package com.example.vgc_project.service;

import com.example.vgc_project.DTO.CinemaDTO;
import com.example.vgc_project.entity.Cinema;
import com.example.vgc_project.repository.CinemaRepository;
import com.example.vgc_project.service.ServiceImp.CinemaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService implements CinemaImp {

    @Autowired
    CinemaRepository cinemaRepository;


    @Override
    public List<CinemaDTO> getAllCinema() {
        List<CinemaDTO> lst = new ArrayList<>();

        for(Cinema c : cinemaRepository.findAll()) {
            lst.add(new CinemaDTO(c.getId_cinema(),c.getName() , c.getSeat()));
        }
        return lst ;
    }

    @Override
    public boolean addCinema(String name, int seat) {
        try{
            Cinema c = new Cinema();
            c.setName(name);
            c.setSeat(seat);
            cinemaRepository.save(c);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
