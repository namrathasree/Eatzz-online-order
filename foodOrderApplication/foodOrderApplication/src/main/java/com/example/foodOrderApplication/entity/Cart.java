package com.example.foodOrderApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long cartId;
    @JsonIgnore
    @ManyToOne
    private Customer customer;
    @JsonIgnore
    @ManyToOne
    private Food food;

    public Cart(){}

    public Long getId() {
        return cartId;
    }

    public void setId(Long id) {
        this.cartId = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
