package com.gkp.main.controller;

import org.springframework.http.ResponseEntity;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gkp.main.entities.BookCategory;
import com.gkp.main.service.CustomerService;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Customer")
/* @PreAuthorize("hasRole('Customer')") */
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;
	
	
	//1.) Get Books By Category 
	/*
	 * @GetMapping("/books/categoryName") public ResponseEntity<List<Book>>
	 * getBooksByCategory(@RequestParam String categoryName){ List<Book> books =
	 * customerService.getBooksByCategory(categoryName); return
	 * ResponseEntity.ok(books); }
	 */
	
	@GetMapping("/viewCategory")
	public List<BookCategory> getCategories() {
	    return customerService.getAllCategories();
	}
	
	
	
	
	
	
	
	
	
	/*
	 * //2.) get the details of a specific book - Read more feature
	 * 
	 * @GetMapping("/books/{bookId}") public ResponseEntity<Book>
	 * getBookDetails(@PathVariable Long bookId){ Book
	 * book=customerService.getBookDetailsByID(bookId); return
	 * ResponseEntity.ok(book); }
	 * 
	 * //3.) Add to cart feature **
	 * 
	 * @PostMapping("/cart/add") public ResponseEntity<Cart> addToCart(
	 * 
	 * @RequestParam Long customerId,
	 * 
	 * @RequestParam Long bookId,
	 * 
	 * @RequestParam int quantity ){ Cart cart =
	 * customerService.addToCart(customerId, bookId, quantity); return
	 * ResponseEntity.ok(cart); }
	 */
}
