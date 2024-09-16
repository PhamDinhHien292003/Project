package com.example.vgc_project.repository;

import com.example.vgc_project.entity.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Integer> {
    Films findAllByid(int id) ;
    @Query(value = "SELECT * FROM film WHERE name_film LIKE %:name_film%", nativeQuery = true)
    List<Films> findAllByNameFilm(@Param("name_film") String nameFilm);
}
