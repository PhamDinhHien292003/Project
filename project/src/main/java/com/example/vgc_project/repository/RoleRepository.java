package com.example.vgc_project.repository;

import com.example.vgc_project.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    @Modifying
    @Query("DELETE FROM roles r WHERE r.user.id = ?1")
    void deleteByUserId(int id);
}
