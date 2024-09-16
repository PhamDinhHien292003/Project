package com.example.vgc_project.entity;


import jakarta.persistence.*;

@Entity(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    private String start;

    @Column(name = "end")
    private String end;

    @Column(name = "price")
    private Double price;

    @OneToOne
    @JoinColumn(name = "id"  , insertable = false, updatable = false)
    private Users users;

    public Long getId_voucher() {
        return id;
    }

    public void setId_voucher(Long id_voucher) {
        this.id = id_voucher;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    // Getters and Setters
}