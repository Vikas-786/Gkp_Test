package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

}
