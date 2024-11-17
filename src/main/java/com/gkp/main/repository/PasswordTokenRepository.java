package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.gkp.main.entities.PasswordResetToken;
import com.gkp.main.entities.User;

import java.util.Optional;




@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
			
				
			Optional<PasswordResetToken>findByToken(String token);
			
			void deleteByUser(User user); // to delete/invalidate 
	
}
