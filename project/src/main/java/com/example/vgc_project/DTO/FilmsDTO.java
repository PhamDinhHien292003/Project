package com.example.vgc_project.DTO;

import java.util.Date;
import java.util.List;

public class FilmsDTO {
    private int id  ;
    private String filmName;
    private Date release_date ;
    private int running_time ;
    private String category_film;
    private int age_req;
    private int likes ;
    private int price ;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ShowingDTO> getShowingDTOList() {
        return showingDTOList;
    }

    public void setShowingDTOList(List<ShowingDTO> showingDTOList) {
        this.showingDTOList = showingDTOList;
    }

    private List<ShowingDTO> showingDTOList ;
    public int getLikes() {
        return likes;
    }

    public FilmsDTO(int id, String filmName, Date release_date, int running_time, String category_film, int age_req, int likes, int price, List<ShowingDTO> showingDTOList, String image) {
        this.id = id;
        this.filmName = filmName;
        this.release_date = release_date;
        this.running_time = running_time;
        this.category_film = category_film;
        this.age_req = age_req;
        this.likes = likes;
        this.price = price;
        this.showingDTOList = showingDTOList;
        this.image = image;
    }

    public FilmsDTO(int id, String filmName, int price) {
        this.id = id;
        this.filmName = filmName;
        this.price = price;
    }

    public FilmsDTO(int id, String filmName, Date release_date, int running_time, String category_film, int age_req, int likes, int price, String image) {
        this.id = id;
        this.filmName = filmName;
        this.release_date = release_date;
        this.running_time = running_time;
        this.category_film = category_film;
        this.age_req = age_req;
        this.likes = likes;
        this.price = price;
        this.image = image;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getRunning_time() {
        return running_time;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }

    public String getCategory_film() {
        return category_film;
    }

    public void setCategory_film(String category_film) {
        this.category_film = category_film;
    }

    public int getAge_req() {
        return age_req;
    }

    public void setAge_req(int age_req) {
        this.age_req = age_req;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public FilmsDTO(int id, String filmName) {
        this.id = id;
        this.filmName = filmName;
    }

    public FilmsDTO() {
    }
}
