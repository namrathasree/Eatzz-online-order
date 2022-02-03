package com.example.foodOrderApplication.controller;

import com.example.foodOrderApplication.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @PostMapping("/adminHome")
    public String adminLogin(HttpServletRequest request, Model model) {
        String adminUserName = request.getParameter("adminuserName");
        String adminPassword = request.getParameter("adminpassword");
        if (adminUserName.equals("admin@123") && adminPassword.equals("admin")) {
            return "adminHome";
        }
        else{
            model.addAttribute("message","Invalid Credentials");
            return "admin";
        }

    }

//    @PostMapping("/")
}

