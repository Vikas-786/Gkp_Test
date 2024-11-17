package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.Rating;


public interface RatingRepository extends JpaRepository<Rating, Long> {

}
