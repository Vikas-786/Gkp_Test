package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
