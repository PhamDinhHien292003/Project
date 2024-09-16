package com.example.vgc_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "showing")
public class Showing {
    @Id
    @Column(name = "id_film")
    private Long id_film;

    @Id
    @Column(name = "id_cinema")
    private Long id_cinema;

    @Column(name = "start")
    private String start;
    @Column(name = "end")
    private String end;

    @Column(name = "day")
    private Date day;



    @ManyToOne
    @JoinColumn(name = "id_film")
    private Films films;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private  Cinema cinema ;



    public Long getId_film() {
        return id_film;
    }

    public void setId_film(Long id_film) {
        this.id_film = id_film;
    }

    public Long getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(Long id_cinema) {
        this.id_cinema = id_cinema;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
