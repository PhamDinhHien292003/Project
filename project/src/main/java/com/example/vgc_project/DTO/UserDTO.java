package com.example.vgc_project.DTO;

import com.example.vgc_project.Enum.Role;
import com.example.vgc_project.entity.Roles;

import java.util.List;

public class UserDTO {
    private long id ;
    private String  name ;
    private int age ;
    private String username;
    private String passowrd ;
    private List<RoleDTO> roles ;

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public UserDTO(long id, String name, int age, String username, String passowrd, List<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.passowrd = passowrd;
        this.roles = roles;
    }

    public UserDTO() {
    }


    public UserDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO(long id, String name, List<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public UserDTO(long id, String name, int age, String username, String passowrd) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.passowrd = passowrd;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }
}
