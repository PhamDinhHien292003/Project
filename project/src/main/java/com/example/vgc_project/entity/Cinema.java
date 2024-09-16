package com.example.vgc_project.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cinema;
    @Column(name = "name")
    private String name;
    @Column(name = "seat")
    private int seat;


    @OneToMany(mappedBy = "cinema")
    private List<Showing> showingList;

    @OneToMany(mappedBy = "cinema")
    private List<Ticket> tickets;

    public List<Showing> getShowingList() {
        return showingList;
    }

    public void setShowingList(List<Showing> showingList) {
        this.showingList = showingList;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(Long id_cinema) {
        this.id_cinema = id_cinema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
