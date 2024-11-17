package com.gkp.main.service;

import org.springframework.stereotype.Service;

import com.gkp.main.entities.Book;
import com.gkp.main.entities.BookCategory;
import com.gkp.main.repository.BookCategoryRespository;
import com.gkp.main.repository.BookRepository;
import com.gkp.main.repository.CartItemRepo;
import com.gkp.main.repository.CartRepository;
import com.gkp.main.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final BookRepository bookRepository;
	private final CustomerRepository customerRepository;
	private final CartRepository cartRepository;
	private final CartItemRepo cartItemRepo;
	private final BookCategoryRespository bookCategoryRespository;

	
	//1.) getBooksByCategoryName 
	/*
	 * public List<Book> getBooksByCategory(String categoryName){ return
	 * bookRepository.findBooksByCategoryName(categoryName); }
	 */
	
	
	public List<BookCategory> getAllCategories(){
		return bookCategoryRespository.findAll();
		}
	
	
	public List<Book> getBooksByCategoryName(String categoryName){
		Optional<BookCategory> category=bookCategoryRespository.findByName(categoryName);
			if(category.isPresent()) {
				return new ArrayList<>(category.get().getBooks());
			}
			else {
				return new ArrayList<>();
			}
		}
	
	
	
	

	
	

	// 2.) get BooksByTitle
	/*
	 * public List<Book> getBookDetails(String title) { return
	 * bookRepository.findByTitle(title).orElseThrow(()-> new
	 * EntityNotFoundException("Book not found")); }
	 */

	// 3.) get Details of a specific book *** read more feature
	/*
	 * public Book getBookDetailsByID(Long id){ return
	 * bookRepository.findById(id).orElseThrow(()-> new
	 * EntityNotFoundException("Book Not found")); }
	 */

	// 4.) add to cart with specified quantity ** Feature
	/*
	 * public Cart addToCart(Long customerId, Long bookId, int quantity) {
	 * 
	 * fist we will fetch customer object from repository Customer customer =
	 * customerRepository.findById(customerId).orElseThrow(()-> new
	 * EntityNotFoundException("Customer Not Found")); uske baad hum uska cart
	 * dekhenge Cart cart = customer.getCart();
	 * 
	 * agr uske pass koi cart nahi hai to hum customer object ko ek naye cart m
	 * assign kr denge
	 * 
	 * if(cart == null) { cart = new Cart(); cart.setCustomer(customer);
	 * cartRepository.save(cart); }
	 * 
	 * Book book = bookRepository.findById(bookId).orElseThrow(()-> new
	 * EntityNotFoundException("Book Not found"));
	 * 
	 * CartItem cartItem = new CartItem(); cartItem.setCart(cart);
	 * cartItem.setBook(book); cartItem.setQuantity(quantity);
	 * cartItem.setPrice(book.getPrice()); cartItemRepo.save(cartItem);
	 * cart.getCartItems().add(cartItem); cart.calculateTotals();
	 * cartRepository.save(cart); return cart; }
	 */

	// order-BuyNow

	// payment

}
