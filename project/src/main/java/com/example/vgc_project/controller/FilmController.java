package com.example.vgc_project.controller;
import com.example.vgc_project.payload.ResponseData;
import com.example.vgc_project.service.ServiceImp.FilesStorageImp;
import com.example.vgc_project.service.ServiceImp.FilmsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@CrossOrigin("*")
@RequestMapping("films")
@RestController
public class FilmController {

    @Autowired
    FilmsImp filmsImp ;

    @Autowired
    FilesStorageImp filesStorageImp ;

    @GetMapping("all_films")
    public ResponseEntity<?> getFilms(){
        return new ResponseEntity<>(new ResponseData(200 , "done" , filmsImp.getAllFilms()) , HttpStatus.OK);
    }

    @PostMapping("all_films_with_name")
    public ResponseEntity<?> getFilmsWithName(@RequestParam String name ){
        return new ResponseEntity<>(new ResponseData(200 , "done" , filmsImp.getFilmsWithName(name)) , HttpStatus.OK);
    }


    @CacheEvict(value = "cacheOfFilms" , allEntries = true)
    @PostMapping("insert")
    public ResponseEntity<?> insertFilms(
            @RequestParam String name_film,
            @RequestParam String release_date,
            @RequestParam int running_time,
            @RequestParam String category_film,
            @RequestParam int age_req,
            @RequestParam int likes,
            @RequestParam MultipartFile image_film) throws ParseException {
        return filmsImp.addFilms(name_film , new SimpleDateFormat("yyyy-MM-dd").parse(release_date), running_time , category_film , age_req ,  likes , image_film) ?
                new ResponseEntity<>(new ResponseData(200 , "done" , "SAVED") , HttpStatus.OK) :
                new ResponseEntity<>(new ResponseData(200 , "ERROR" , null) , HttpStatus.OK);
    }



    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @PostMapping("film")
    public ResponseEntity<?> getFilm(@RequestParam int id){
        return new ResponseEntity<>(new ResponseData(200,"done",filmsImp.getFilm(id) ) , HttpStatus.OK);
    }


    @GetMapping("getDateFilm")
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(new ResponseData(200 , "done" , filmsImp.getAllShowingDate()) , HttpStatus.OK);
    }


    @PostMapping("getShowingList")
    public ResponseEntity<?> getShowing(@RequestParam int id){
        return new ResponseEntity<>(new ResponseData(200,"done",filmsImp.getShowingDate(id) ) , HttpStatus.OK);
    }


    @CacheEvict(value = "cacheOfFilms" , allEntries = true)
    @PostMapping("del_from_id")
    public ResponseEntity<?> delFilms(@RequestParam int id){
        return new ResponseEntity<>(new ResponseData(200,"successfully", filmsImp.deleteFilms(id)  ) , HttpStatus.OK);
    }

    @CacheEvict(value = "cacheOfFilms" , allEntries = true)
    @PostMapping("updateID")
    public ResponseEntity<?> updateID(
            @RequestParam int id,
            @RequestParam String name_film,
            @RequestParam String release_date,
            @RequestParam String category_film,
            @RequestParam(required = false) MultipartFile image_film) throws ParseException{
        return new ResponseEntity<>(new ResponseData(200 , "successfully" , filmsImp.updateFilms(id , name_film , new SimpleDateFormat("yyyy-MM-dd").parse(release_date) , category_film ,image_film)  ) , HttpStatus.OK);
    }   
}
