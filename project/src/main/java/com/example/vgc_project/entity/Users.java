package com.example.vgc_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "User")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;





    @OneToMany(mappedBy = "user")
    private List<Roles> role ;


    public List<Roles> getRoles() {
        return role;
    }

    public void setRoles(List<Roles>  role) {
        this.role = role;
    }

    public List<Roles> getRole() {
        return role;
    }

    public void setRole(List<Roles> role) {
        this.role = role;
    }

    @OneToOne
    @JoinColumn(name = "id" , insertable = false, updatable = false)
    private Voucher voucher;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "users")
    private List<Ticket> ticketList;

    public List<Ticket> getTicketList() {
        return ticketList;
    }


    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}