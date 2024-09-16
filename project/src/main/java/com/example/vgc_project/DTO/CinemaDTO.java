package com.example.vgc_project.DTO;

public class CinemaDTO {
    private long id ;
    private String name ;
    private int seat ;


    public CinemaDTO(long id, String name, int seat) {
        this.id = id;
        this.name = name;
        this.seat = seat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public CinemaDTO() {
    }

    public CinemaDTO(String name, int seat) {
        this.name = name;
        this.seat = seat;
    }
}
