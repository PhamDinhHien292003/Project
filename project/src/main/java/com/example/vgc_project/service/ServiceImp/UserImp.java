package com.example.vgc_project.service.ServiceImp;

import com.example.vgc_project.DTO.UserDTO;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserImp {
    public List<UserDTO> getAllUser();

    public UserDTO getUserWithID(int id);


    public String AddUser(String name , int age , String username , String password  );


    public UserDTO checklogin(String username, String password);


    public boolean delUser(int id ) ;
}
