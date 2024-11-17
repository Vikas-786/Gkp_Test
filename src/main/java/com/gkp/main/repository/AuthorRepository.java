package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gkp.main.entities.Author;
import com.gkp.main.entities.BookCategory;

import java.util.*;


public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	List<Author> findByCategory(BookCategory category);
	
	 Optional<Author> findByName(String name);

}
