package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
