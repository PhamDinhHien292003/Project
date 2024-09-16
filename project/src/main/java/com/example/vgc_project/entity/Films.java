package com.example.vgc_project.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "Film")
public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_film")
    private String name_film;
    @Column(name = "release_date")
    private Date release_date;
    @Column(name = "running_time")
    private int running_time;

    @Column(name = "category_film")
    private String category_film;
    @Column(name = "age_req")
    private int age_req;
    @Column(name = "likes")
    private int likes;

    @Column(name = "price")
    private int price;

    @Column(name = "image_film")
    private String image_film;

    public String getImage_film() {
        return image_film;
    }

    public void setImage_film(String image_film) {
        this.image_film = image_film;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "films")
    private List<Showing> showingList;

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Showing> getShowingList() {
        return showingList;
    }

    public void setShowingList(List<Showing> showingList) {
        this.showingList = showingList;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getAge_req() {
        return age_req;
    }

    public void setAge_req(int age_req) {
        this.age_req = age_req;
    }

    public String getCategory_film() {
        return category_film;
    }

    public void setCategory_film(String category_film) {
        this.category_film = category_film;
    }

    public int getRunning_time() {
        return running_time;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getName_film() {
        return name_film;
    }

    public void setName_film(String name_film) {
        this.name_film = name_film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "films")
    private List<Ticket> ticketList;



}
