package com.example.vgc_project.DTO;


import java.util.Date;

public class ShowingDTO {
    private String start;
    private String end ;
    private String date;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public ShowingDTO() {
    }

    public CinemaDTO getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDTO cinema) {
        this.cinema = cinema;
    }


    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    private CinemaDTO cinema;


    public ShowingDTO(String start, String end, String date,  CinemaDTO cinema) {
        this.start = start;
        this.end = end;
        this.date = date;

        this.cinema = cinema;
    }


}
