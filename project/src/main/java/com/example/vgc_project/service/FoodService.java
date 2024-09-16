package com.example.vgc_project.service;

import com.example.vgc_project.DTO.FoodsDTO;
import com.example.vgc_project.entity.Foods;
import com.example.vgc_project.repository.FoodsRepository;
import com.example.vgc_project.service.ServiceImp.FoodImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodService implements FoodImp {

    @Autowired
    FoodsRepository foodsRepository;

    @Override
    public List<FoodsDTO> getAllFood() {
        List<FoodsDTO> lstFoods = new ArrayList<FoodsDTO>();
        for (Foods food : foodsRepository.findAll()) {
            lstFoods.add(new FoodsDTO(food.getName_food() , food.getPrice(), food.getImage())) ;}
        return lstFoods ;
    }
}
