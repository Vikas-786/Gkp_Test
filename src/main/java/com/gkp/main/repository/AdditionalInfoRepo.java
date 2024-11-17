package com.gkp.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.AdditionalInformation;




public interface AdditionalInfoRepo extends JpaRepository<AdditionalInformation, Long> {

	Optional<AdditionalInformation> findByIsbn(String isbn);
}
