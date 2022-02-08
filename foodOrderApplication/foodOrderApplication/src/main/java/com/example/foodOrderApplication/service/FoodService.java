package com.example.foodOrderApplication.service;

import com.example.foodOrderApplication.entity.Food;
import com.example.foodOrderApplication.entity.Hotel;
import com.example.foodOrderApplication.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public void addMenu(Food menu){
      foodRepository.save(menu);
    }

    public List<Food> foodList(Long id) {
        return foodRepository.findByHotelId(id);
    }
}
