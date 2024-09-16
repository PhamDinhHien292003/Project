package com.example.vgc_project.service;

import com.example.vgc_project.DTO.CinemaDTO;
import com.example.vgc_project.DTO.FilmsDTO;
import com.example.vgc_project.DTO.ShowingDTO;
import com.example.vgc_project.entity.Cinema;
import com.example.vgc_project.entity.Films;
import com.example.vgc_project.entity.Showing;
import com.example.vgc_project.repository.FilmsRepository;
import com.example.vgc_project.service.ServiceImp.FilmsImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FilmsService implements FilmsImp {

    @Autowired
    FilmsRepository filmsRepository;


    Logger log  = LoggerFactory.getLogger(this.getClass());

    @Cacheable("cacheOfFilms")
    @Override
    public List<FilmsDTO> getAllFilms() {
        final List<Films> films = filmsRepository.findAll();
        List<FilmsDTO> lstFilms = new ArrayList<>();

        for(Films film : films) {

            try {
                lstFilms.add(new FilmsDTO(film.getId()
                        ,film.getName_film()
                        , film.getRelease_date()
                        , film.getRunning_time()
                        , film.getCategory_film()
                        , film.getAge_req()
                        , film.getLikes() , film.getPrice()
                        , film.getImage_film() ) );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return lstFilms ;


    }

    @Override
    public Boolean addFilms(String name, Date release, int runningTime, String cate, int age_req ,int likes , MultipartFile image) {
        Films films = new Films();
        films.setName_film(name);
        films.setRelease_date(release);
        films.setRunning_time(runningTime);
        films.setCategory_film(cate);
        films.setAge_req(age_req);
        films.setLikes(likes);
        films.setImage_film(image.getOriginalFilename());
        try {

            filmsRepository.save(films);
            return true ;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false ;
        }

    }

    @Override
    public List<FilmsDTO> getFilmsWithName(String name) {
        List<Films> films  = filmsRepository.findAllByNameFilm(name);
        List<FilmsDTO> lst = new ArrayList<>();
        for (Films film : films) {
            try {
                lst.add(new FilmsDTO(film.getId()
                        ,film.getName_film()
                        , film.getRelease_date()
                        , film.getRunning_time()
                        , film.getCategory_film()
                        , film.getAge_req()
                        , film.getLikes(), film.getPrice()
                        , film.getImage_film() ) );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return lst ;
    }

    public String formatDate(String str){
        String input = str;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(str);
            String formattedDate = formatter.format(date);
            return formattedDate;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public FilmsDTO getFilm(int id) {
        Films film = filmsRepository.findAllByid(id);
        try {

            return new FilmsDTO(film.getId()
                    ,film.getName_film()
                    , film.getRelease_date()
                    , film.getRunning_time()
                    , film.getCategory_film()
                    , film.getAge_req()
                    , film.getLikes(), film.getPrice()
                    , film.getImage_film() ) ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null ;
        }
    }

    @Override
    public List<FilmsDTO> getAllShowingDate() {
        List<FilmsDTO> lstFilms = new ArrayList<>();
        for(Films film : filmsRepository.findAll()) {
            List<ShowingDTO> showDTO = new ArrayList<>();
            for(Showing eachShow :  film.getShowingList()){
                showDTO.add(new ShowingDTO(eachShow.getStart() , eachShow.getEnd() ,formatDate(eachShow.getDay().toString()), new CinemaDTO( eachShow.getCinema().getId_cinema(),eachShow.getCinema().getName() , eachShow.getCinema().getSeat())));
            }
            try {
                lstFilms.add(new FilmsDTO(film.getId()
                        , film.getName_film()
                        , film.getRelease_date()
                        , film.getRunning_time()
                        , film.getCategory_film()
                        , film.getAge_req()
                        , film.getLikes() , film.getPrice()
                        , showDTO, film.getImage_film())
                        );
            }catch (Exception ex ){
                System.out.println(ex.getMessage());
                return null ;
            }
        }
        return lstFilms;
    }

    @Override
    public List<ShowingDTO> getShowingDate(int id) {
        Films film = filmsRepository.findAllByid(id);
        try {
            List<ShowingDTO> showDTO =  new ArrayList<>();
            for (Showing eachShow :film.getShowingList() ) {
                showDTO.add(new ShowingDTO(eachShow.getStart() , eachShow.getEnd() ,formatDate(eachShow.getDay().toString()),  new CinemaDTO( eachShow.getCinema().getId_cinema(), eachShow.getCinema().getName() , eachShow.getCinema().getSeat())));
            }
            return showDTO ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null ;
        }
    }

    @Override
    public String deleteFilms(int id) {
        String message = "" ;
        if(filmsRepository.existsById(id)) {
            try {
                filmsRepository.deleteById(id);
                return "successfully";
            }
            catch (Exception ex){
                return ex.getMessage();
            }
        }
        else {
            return "Films isn't exist!";
        }
    }

    @Override
    public Boolean updateFilms(int id , String name, Date release, String cate, MultipartFile image) {
        if(filmsRepository.existsById(id)) {
            try {
            Films films = filmsRepository.findAllByid(id);
            films.setName_film(name);
            films.setRelease_date(release);
            films.setCategory_film(cate);
            if(image != null ){
                films.setImage_film(image.getOriginalFilename());
            }
                filmsRepository.save(films);
                return true ;
            }
            catch (Exception ex ){
                ex.printStackTrace();
                return false ;
            }
        }
        else return false ;
    }

}
