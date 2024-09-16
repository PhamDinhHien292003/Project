package com.example.vgc_project.service.ServiceImp;

import com.example.vgc_project.DTO.FilmsDTO;
import com.example.vgc_project.DTO.ShowingDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface FilmsImp {
    public List<FilmsDTO> getAllFilms();
    public Boolean addFilms(String name , Date release , int runningTime , String cate , int age_req ,int likes ,  MultipartFile image);
    public List<FilmsDTO> getFilmsWithName(String name );
    public FilmsDTO getFilm(int id);
    public List<FilmsDTO> getAllShowingDate() ;

    public List<ShowingDTO> getShowingDate(int id);



    public String deleteFilms(int id);

    public Boolean updateFilms(int id  , String name , Date release, String cate ,  MultipartFile image);

}
