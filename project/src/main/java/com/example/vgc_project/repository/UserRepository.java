package com.example.vgc_project.repository;

import com.example.vgc_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
     Users findById(int id);


     Users findByUsername(String username);

}
