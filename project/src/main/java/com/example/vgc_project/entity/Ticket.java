package com.example.vgc_project.entity;


import jakarta.persistence.*;

@Entity(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user" )
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_film")
    private Films films;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;

    @OneToOne
    @JoinColumn(name = "id")
    private DetailTicket detailTicket;


    @Column(name = "quantity")
    private int quantity;

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DetailTicket getDetailTicket() {
        return detailTicket;
    }

    public void setDetailTicket(DetailTicket detailTicket) {
        this.detailTicket = detailTicket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Films getFilm() {
        return films;
    }

    public void setFilm(Films film) {
        this.films = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    // Getters and Setters
}