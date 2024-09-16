package com.example.vgc_project.controller;


import com.example.vgc_project.entity.Ticket;
import com.example.vgc_project.payload.ResponseData;
import com.example.vgc_project.service.ServiceImp.CinemaImp;
import com.example.vgc_project.service.ServiceImp.TicketImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("ticket")
@RestController
public class TicketController {
    @Autowired
    TicketImp  ticketImp;


    @GetMapping("get")
    public ResponseEntity<?> getAllTicket() {
        return new ResponseEntity<>(new ResponseData(200 , "ok" , ticketImp.getAllTicket()) , HttpStatus.OK);
    }


    @PostMapping("buy_ticket")
    public ResponseEntity<?> buyTicket(@RequestParam int id_user ,
                                       @RequestParam int id_films ,
                                       @RequestParam int id_cinema,
                                       @RequestParam int quantity
                                       ) {
        return new ResponseEntity<>(new ResponseData(200 , "ok" , ticketImp.addTicket(id_user, id_films , id_cinema , quantity)) , HttpStatus.OK);
    }
}
