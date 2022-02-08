package com.example.foodOrderApplication.controller;

import com.example.foodOrderApplication.entity.Food;
import com.example.foodOrderApplication.entity.Hotel;
import com.example.foodOrderApplication.service.FoodService;
import com.example.foodOrderApplication.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private FoodService foodService;


    @PostMapping("/adminHome")
    public String adminLogin(HttpServletRequest request, Model model) {
        String adminUserName = request.getParameter("adminuserName");
        String adminPassword = request.getParameter("adminpassword");
        if (adminUserName.equals("admin@123") && adminPassword.equals("admin")) {
            return "adminHome";
        } else {
            model.addAttribute("message", "Invalid Credentials");
            return "admin";
        }

    }

    @GetMapping("/viewHotels")
    public String viewHotels(HttpServletRequest request,Model model) {
        model.addAttribute("adminhotels", hotelService.hotelList());
        return "viewHotels";
    }
    @GetMapping("/viewHotels/add")
    public String addhotels(Model model){
    model.addAttribute("hotel",new Hotel());
    return "addHotel";
    }
    @PostMapping("viewHotels/add")
    public String addhotel(@ModelAttribute("hotel") Hotel hotel){
    hotelService.addHotel(hotel);
    return "redirect:/viewHotels";
    }
    @GetMapping("/viewHotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id){
        hotelService.removeHotelById(id);
        return "redirect:/viewHotels";
    }

    @RequestMapping("/updateMenu/{id}")
    public String updateMenu(@PathVariable String id, Model model){
        model.addAttribute("food",id);
        model.addAttribute("menu",new Food());
        return "updateMenu";
    }
    @PostMapping("/updateMenu/{id}")
    public String updateMenu(@ModelAttribute("menu") Food menu){
        foodService.addMenu(menu);
        return "redirect:/viewHotels";
    }
    @GetMapping("/viewMenu/{id}")
    public String viewMenu(@PathVariable Long id, HttpServletRequest request, Model model) {
        model.addAttribute("menu",id);
        model.addAttribute("adminmenu", foodService.foodList(id));
        return "viewMenu";
    }
}

