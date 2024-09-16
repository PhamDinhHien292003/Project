package com.example.vgc_project.service.ServiceImp;

import com.example.vgc_project.DTO.TicketDTO;

import java.util.List;

public interface TicketImp {
    public List<TicketDTO> getAllTicket();


    public Boolean addTicket(int id_user , int id_films , int id_cinema , int quantity);
}
