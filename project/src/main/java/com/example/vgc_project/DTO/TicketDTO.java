package com.example.vgc_project.DTO;

public class TicketDTO {
    private long id ;
    private UserDTO userDTO ;
    private FilmsDTO filmsDTO;
    private CinemaDTO  cinemaDTO;
    private int quantity ;

    public TicketDTO(long id, UserDTO userDTO, FilmsDTO filmsDTO, CinemaDTO cinemaDTO , int quantity) {
        this.id = id;
        this.userDTO = userDTO;
        this.filmsDTO = filmsDTO;
        this.cinemaDTO = cinemaDTO;
        this.quantity = quantity ;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TicketDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FilmsDTO getFilmsDTO() {
        return filmsDTO;
    }

    public void setFilmsDTO(FilmsDTO filmsDTO) {
        this.filmsDTO = filmsDTO;
    }

    public CinemaDTO getCinemaDTO() {
        return cinemaDTO;
    }

    public void setCinemaDTO(CinemaDTO cinemaDTO) {
        this.cinemaDTO = cinemaDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
