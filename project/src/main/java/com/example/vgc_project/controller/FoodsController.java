package com.example.vgc_project.controller;


import com.example.vgc_project.DTO.FoodsDTO;
import com.example.vgc_project.payload.ResponseData;
import com.example.vgc_project.service.ServiceImp.FilesStorageImp;
import com.example.vgc_project.service.ServiceImp.FoodImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("foods")
@RestController
public class FoodsController {

    @Autowired
    private FoodImp foodImp ;

    @Autowired
    private FilesStorageImp filesStorageImp ;


    @GetMapping("getfoods")
    public ResponseEntity<?> getALlFoods(){
        return new ResponseEntity<>(new ResponseData(200 , "done" , foodImp.getAllFood()) , HttpStatus.OK) ;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
