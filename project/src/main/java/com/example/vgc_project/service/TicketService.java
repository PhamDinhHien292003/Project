package com.example.vgc_project.service;

import com.example.vgc_project.DTO.CinemaDTO;
import com.example.vgc_project.DTO.FilmsDTO;
import com.example.vgc_project.DTO.TicketDTO;
import com.example.vgc_project.DTO.UserDTO;
import com.example.vgc_project.entity.Cinema;
import com.example.vgc_project.entity.Films;
import com.example.vgc_project.entity.Ticket;
import com.example.vgc_project.entity.Users;
import com.example.vgc_project.repository.CinemaRepository;
import com.example.vgc_project.repository.FilmsRepository;
import com.example.vgc_project.repository.TicketRepository;
import com.example.vgc_project.repository.UserRepository;
import com.example.vgc_project.service.ServiceImp.TicketImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements TicketImp {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository ;

    @Autowired
    FilmsRepository filmsRepository;

    @Autowired
    CinemaRepository cinemaRepository;


    @Override
    public List<TicketDTO> getAllTicket() {

       List<TicketDTO> ticketDTOS = new ArrayList<>();
        for(Ticket ticket  : ticketRepository.findAll()) {
            ticketDTOS.add(new TicketDTO(ticket.getId() ,
                    new UserDTO(ticket.getUsers().getId() , ticket.getUsers().getName()) ,
                    new FilmsDTO(ticket.getFilm().getId()
                            , ticket.getFilm().getName_film() ,ticket.getFilm().getPrice()
                            ), new CinemaDTO(ticket.getCinema().getName(), ticket.getCinema().getSeat()) ,
                    ticket.getQuantity()));
        }
        return ticketDTOS;
    }

    @Override
    public Boolean addTicket(int id_user, int id_films, int id_cinema, int quantity) {

        /// Get user with id -> to DTO
        Users users = userRepository.findById(id_user);

        /// Get films from id
        Films films = filmsRepository.findAllByid(id_films);

        ////Get cinema by id val using optional because cinema val can have null value   ....
        Optional<Cinema> cinema = cinemaRepository.findById((long)id_cinema);

        Ticket ticket = new Ticket();
        ticket.setUsers(users);
        ticket.setFilms(films);
        ticket.setCinema(cinema.get());
        ticket.setQuantity(quantity);
        ticketRepository.save(ticket);
        return true;
    }
}
