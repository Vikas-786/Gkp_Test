package com.gkp.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gkp.main.dto.BookCategoryDTO;
import com.gkp.main.dto.BookDTO;
import com.gkp.main.entities.AdditionalInformation;
import com.gkp.main.entities.Author;
import com.gkp.main.entities.Book;
import com.gkp.main.entities.BookCategory;
import com.gkp.main.entities.Distributor;
import com.gkp.main.service.AdminService;
import com.gkp.main.service.CustomerService;

import java.util.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
/* @PreAuthorize("hasRole('Admin')") */
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	// Add BookCategory
    @PostMapping("/addBookCategory")
    public BookCategory addBookCategory(@RequestBody BookCategoryDTO bookCategoryDTO) {
        return adminService.addCategory(bookCategoryDTO);   
    }
    
 // Get All BookCategories
    @GetMapping("/getAllBookCategories")
    public List<BookCategory> getAllBookCategories() {
        return adminService.getAllBookCategories();
    }

    // Update BookCategory
    @PutMapping("/updateBookCategory/{id}")
    public BookCategory updateBookCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        return adminService.updateBookCategory(id, bookCategoryDTO);
    }

    // Delete BookCategory
    @DeleteMapping("/deleteBookCategory/{id}")
    public boolean deleteBookCategory(@PathVariable Long id) {
        return adminService.deleteBookCategory(id);
    }

    // Add Single Book
    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
    	try {
            // Call the service to add the book
           Book addedBook=adminService.saveBook(bookDTO);
            return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occured:" + e.getMessage());
        }
    }
    
    
    //Add multiple Books 
    @PostMapping("/addMultipleBooks")
    public ResponseEntity<List<Book>> addBooks(@RequestBody List<BookDTO> bookDTOs) {
        List<Book> savedBooks = adminService.saveBooks(bookDTOs);
        return ResponseEntity.ok(savedBooks);
    }
    
    
    //view Books 
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = adminService.getBooks();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content if no books are found
        }
        return ResponseEntity.ok(books); // Return 200 OK with the list of books
    }
    
    
    //
    
    
    
    
    

    // Update Book
   /* @PutMapping("/updateBook/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return adminService.updateBook(id, bookDTO);
    }

    // Delete Book
    @DeleteMapping("/deleteBook/{id}")
    public boolean deleteBook(@PathVariable Long id) {
        return adminService.deleteBook(id);
    }*/
}