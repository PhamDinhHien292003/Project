package com.example.vgc_project.DTO;

import org.springframework.boot.context.properties.bind.DefaultValue;


public class RoleDTO {

    private String role ;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoleDTO() {
    }

    public RoleDTO( String role) {
        this.role = role;
    }
}
