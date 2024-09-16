package com.example.vgc_project.controller;

import com.example.vgc_project.DTO.UserDTO;
import com.example.vgc_project.jwt.UserJwt;
import com.example.vgc_project.payload.ResponseData;
import com.example.vgc_project.service.ServiceImp.UserImp;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.beans.Encoder;


@CrossOrigin("*")
@RestController
@RequestMapping("Users")
public class UsersController {
    @Autowired
    private UserImp userImp ;

    @Autowired
    private UserJwt userJwt;


    Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("getAllUser")
    private ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(new ResponseData(200 , "successfully", userImp.getAllUser()) , HttpStatus.OK);
    }


    @PostMapping("get")
    private ResponseEntity<?> getUserWithID(@RequestParam int id ){
        return new ResponseEntity<>(new ResponseData(200 , "done" , userImp.getUserWithID(id)) , HttpStatus.OK);
    }


    @PostMapping("login")
    private ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password){
        UserDTO user = userImp.checklogin(username , password )  ;
        return new ResponseEntity<>(new ResponseData(200 , user == null ? "error" : "successfully " + user.getId() ,userJwt.generateToken(user)) , HttpStatus.OK);
    }

    @PostMapping("sign_up")
    private ResponseEntity<?> signUpUser( @RequestParam String name , @RequestParam int age  , @RequestParam String username, @RequestParam String password ){
        return new  ResponseEntity<>(new ResponseData(200 , "OK" ,userImp.AddUser(name , age , username , password)) , HttpStatus.OK);
    }


    @PostMapping("del_with_id")
    private ResponseEntity<?> signUpUser( @RequestParam int id ){
        return new  ResponseEntity<>(new ResponseData(200 , "OK" ,userImp.delUser(id)) , HttpStatus.OK);
    }
}
