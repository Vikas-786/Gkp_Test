package com.gkp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gkp.main.entities.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	public Long countByCategory(BookCategoryRespository bookCategory);
	
	@Query("SELECT b FROM Book b JOIN b.category c WHERE c.name = :categoryName")
	List<Book> findBooksByCategoryName(@Param("categoryName") String categoryName);

	public Optional<List<Book>> findByTitle(String title);
	
}
