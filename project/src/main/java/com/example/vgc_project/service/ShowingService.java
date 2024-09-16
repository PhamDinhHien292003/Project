package com.example.vgc_project.service;

import com.example.vgc_project.DTO.CinemaDTO;
import com.example.vgc_project.DTO.FilmsDTO;
import com.example.vgc_project.DTO.ShowingDTO;
import com.example.vgc_project.entity.Cinema;
import com.example.vgc_project.entity.Films;
import com.example.vgc_project.entity.Showing;
import com.example.vgc_project.repository.ShowingRepository;
import com.example.vgc_project.service.ServiceImp.ShowingImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class ShowingService {
///implements ShowingImp {
//    @Autowired
//    private ShowingRepository showingRepository;
//
//    @Override
//    public List<ShowingDTO> getallDate() {
//        List<ShowingDTO> showingDTOList = new ArrayList<>();
//        for (Showing showing : showingRepository.findAll()) {
//            ShowingDTO show = new ShowingDTO();
//            show.setStart(showing.getStart());
//            show.setEnd(showing.getEnd());
//
//            /// get cinema from loop
//            Cinema cinema = showing.getCinema();
//            CinemaDTO cinemaDTO = new CinemaDTO();
//            cinemaDTO.setName(cinema.getName());
//            cinemaDTO.setSeat(cinema.getSeat());
//            show.setCinema(cinemaDTO);
//
//            /// get films from loop
//            Films film = showing.getFilms();
//
//            try {
//                FilmsDTO filmsDTO =  new FilmsDTO(film.getId()
//                        ,film.getName_film()
//                        , new SimpleDateFormat("yyyy-MM-dd").parse(film.getRelease_date().toString())
//                        , film.getRunning_time()
//                        , film.getCategory_film()
//                        , film.getAge_req()
//                        , film.getLikes()
//                        , film.getImage_film() ) ;
//                show.setFilm(filmsDTO);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            showingDTOList.add(show) ;
//        }
//        return showingDTOList;
//    }
}
