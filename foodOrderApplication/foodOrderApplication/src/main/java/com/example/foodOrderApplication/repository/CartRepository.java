package com.example.foodOrderApplication.repository;

import com.example.foodOrderApplication.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public List<Cart> findByCustomerUserName(String userName);

//    public boolean existsByFoodsId(Long id);

//    public Cart findByDressesId(Long id);
}
