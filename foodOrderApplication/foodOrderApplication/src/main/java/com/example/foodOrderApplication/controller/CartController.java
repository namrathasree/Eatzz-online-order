package com.example.foodOrderApplication.controller;

import com.example.foodOrderApplication.entity.Cart;
import com.example.foodOrderApplication.entity.Customer;
import com.example.foodOrderApplication.entity.Food;
import com.example.foodOrderApplication.service.CartService;
import com.example.foodOrderApplication.service.CustomerService;
import com.example.foodOrderApplication.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FoodService foodService;
//    private DressesService dressesService;

    @GetMapping("/{userName}/cart/{id}")
    public String cart( @PathVariable String userName,@PathVariable Long id, Model model) {
        model.addAttribute("userName", userName);
        Food food = foodService.getById(id);
        Customer customer = customerService.findCustomerByUserName(userName);
        model.addAttribute("food", food);
        Cart cart = new Cart();
        cart.setFood(food);
        cart.setCustomer(customer);
        cartService.addToCart(cart);
//        model.addAttribute("cart.food.name",cart.getFood().getName());
        model.addAttribute("mycart",cartService.myCart(userName));
        return "cart";
    }
    @GetMapping("/{userName}/cart")
        public String mycart(@PathVariable String userName,Model model){
        model.addAttribute("mycart",cartService.myCart(userName));
        return "cart";
        }
        @DeleteMapping("/cart/{cartId}/{userName}")
    public String removeFromCart(@PathVariable Long cartId,@PathVariable String userName,Model model){
        model.addAttribute("userName",userName);
        cartService.remove(cartId);
                return "redirect:/";
        }
//    @DeleteMapping("/cart/{cartId}/{username}")
//    public String removeFromCart(@PathVariable Long cartId,@PathVariable String username,Model model){
//        model.addAttribute("username",username);
//        cartService.remove(cartId);
//        return "redirect:/"+username+"/cart";
//    }
//}
}
