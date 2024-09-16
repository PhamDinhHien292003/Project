package com.example.vgc_project.controller;

import com.example.vgc_project.payload.ResponseData;
import com.example.vgc_project.service.ServiceImp.CinemaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("cinema")
@RestController
public class CinemaController {
    @Autowired
    private CinemaImp cinemaImp ;


    @GetMapping("getAllRoom")
    public ResponseEntity<?> getAllRoom(){
        return new ResponseEntity<>( new ResponseData(200 , "done" , cinemaImp.getAllCinema()) , HttpStatus.OK);
    }


    @PostMapping("insertRoom")
    public ResponseEntity<?> insertRoom(@RequestParam String nameRoom , @RequestParam int seat ){
        return cinemaImp.addCinema(nameRoom , seat ) ? new ResponseEntity<>( new ResponseData(200 , "done" , true) , HttpStatus.OK)
                :new ResponseEntity<>( new ResponseData(200 , "done" , false) , HttpStatus.OK) ;
    }




}
