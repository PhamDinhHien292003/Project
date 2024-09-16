package com.example.vgc_project.entity;


import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity(name = "roles")
public class Roles {


    public Roles() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;


    public Roles(String name, Users user) {
        this.name = name;
        this.user = user;
    }

    @Column(name = "name_role")
    private String name ;  

    @ManyToOne
    @JoinColumn(name = "id_user" )
    private Users user ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
