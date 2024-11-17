package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
