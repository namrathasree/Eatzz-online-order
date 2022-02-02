package com.example.foodOrderApplication.controller;

import com.example.foodOrderApplication.entity.Customer;
import com.example.foodOrderApplication.service.CustomerService;
import com.example.foodOrderApplication.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
        @Autowired
        private CustomerService customerService;
        @Autowired
        private HotelService hotelService;

    //Home
    @GetMapping("/")
    public String home(){
        return "home";
    }

    //admin
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    //Registration- after registering user will go to login page
    @GetMapping("/register")
    public String registrationForm() {
        return "register";
    }
    @PostMapping("/login")
    public String registration(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String phoneNumber=request.getParameter("phoneNumber");
        Customer customer = new Customer(userName, name, address, password,phoneNumber);
        customerService.createCustomer(customer);
        return "login";
    }

    //Login - after login user will go to the list of movies page
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/hotels")
    public String hotels(Model model){
        model.addAttribute("hotels",hotelService.hotelList());
        return "hotels";
    }

    @PostMapping("/hotels")
    public String login(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Customer customer;
        if (customerService.existsById(userName)) {
            customer = customerService.findCustomerByUserName(userName);
            if (password.equals(customer.getPassword())) {
                model.addAttribute("userName", userName);
                model.addAttribute("hotels", hotelService.hotelList() );
            } else {
                model.addAttribute("message", "Wrong Password");
                return "login";
                //return "redirect:/login";
            }
        } else {
            model.addAttribute("message", "Please enter valid User Name");
            return "login";
            //return "redirect:/login";
        }
        return "hotels";
    }
}
