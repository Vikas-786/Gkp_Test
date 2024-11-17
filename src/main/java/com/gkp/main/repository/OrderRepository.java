package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
