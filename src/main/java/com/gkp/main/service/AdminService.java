package com.gkp.main.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gkp.main.dto.AdditionalInfomationDTO;
import com.gkp.main.dto.AuthorDTO;
import com.gkp.main.dto.BookCategoryDTO;
import com.gkp.main.dto.BookDTO;
import com.gkp.main.entities.AdditionalInformation;
import com.gkp.main.entities.Author;
import com.gkp.main.entities.Book;
import com.gkp.main.entities.BookCategory;
import com.gkp.main.entities.Customer;
import com.gkp.main.entities.Distributor;
import com.gkp.main.entities.Role;
import com.gkp.main.entities.User;
import com.gkp.main.repository.AdditionalInfoRepo;
import com.gkp.main.repository.AuthorRepository;
import com.gkp.main.repository.BookCategoryRespository;
import com.gkp.main.repository.BookRepository;
import com.gkp.main.repository.CustomerRepository;
import com.gkp.main.repository.DistributorRepository;
import com.gkp.main.repository.UserRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AuthorRepository authorRepository;
	private final BookCategoryRespository bookCategoryRespository;
	private final BookRepository bookRepository;
	private final DistributorRepository distributorRepository;
	private final CustomerRepository customerRepository;
	private final UserRepository userRepository;
	private final AdditionalInfoRepo additionalInfoRepo;

	// Add BookCategory
	public BookCategory addCategory(BookCategoryDTO bookCategoryDTO) {
		BookCategory category = new BookCategory();
		category.setName(bookCategoryDTO.getName());
		return bookCategoryRespository.save(category);
	}

	// Get All BookCategories
	public List<BookCategory> getAllBookCategories() {
		return bookCategoryRespository.findAll();
	}

	// Update BookCategory
	public BookCategory updateBookCategory(Long id, BookCategoryDTO bookCategoryDTO) {
		Optional<BookCategory> optionalCategory = bookCategoryRespository.findById(id);
		if (optionalCategory.isPresent()) {
			BookCategory category = optionalCategory.get();
			category.setName(bookCategoryDTO.getName());
			return bookCategoryRespository.save(category);
		}
		return null;
	}

	// Delete BookCategory

	public boolean deleteBookCategory(Long id) {
		Optional<BookCategory> optionalCategory = bookCategoryRespository.findById(id);
		if (optionalCategory.isPresent()) {
			bookCategoryRespository.delete(optionalCategory.get());
			return true;
		}
		return false;
	}

	// Add Single Book
	public Book saveBook(BookDTO bookDTO) {
		// Map BookDTO to Book entity
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setLanguage(bookDTO.getLanguage());
		book.setDescription(bookDTO.getDescription());
		book.setPrice(bookDTO.getPrice());

		// Handle Author
		Author author = authorRepository.findByName(bookDTO.getAuthorName()).orElseGet(() -> {
			Author newAuthor = new Author();
			newAuthor.setName(bookDTO.getAuthorName());
			return authorRepository.save(newAuthor);
			// Save new author
		});
		book.setAuthor(author);

		// Handle Category
		BookCategory category = bookCategoryRespository.findByName(bookDTO.getCategoryName()).orElseGet(() -> {
			BookCategory newCategory = new BookCategory();
			newCategory.setName(bookDTO.getCategoryName());
			return bookCategoryRespository.save(newCategory); // Save new category
		});
		book.setCategory(category);

		// Handle Additional Information
		AdditionalInformation additionalInfo = new AdditionalInformation();
		AdditionalInfomationDTO infoDTO = bookDTO.getAdditionalInfomationDTO();
		if (infoDTO != null) {
			additionalInfo.setWeight(infoDTO.getWeight());
			additionalInfo.setDimensions(infoDTO.getDimensions());
			additionalInfo.setPublisher(infoDTO.getPublisher());
			additionalInfo.setLanguage(infoDTO.getLanguage());
			additionalInfo.setPublishingYear(infoDTO.getPublishingYear());
			additionalInfo.setTotalPages(infoDTO.getTotalPages());
			additionalInfo.setEdition(infoDTO.getEdition());
			additionalInfo.setBuyFromAmazonLink(infoDTO.getBuyFromAmazonLink());
			additionalInfo = additionalInfoRepo.save(additionalInfo); // Save additional info
		}
		book.setAdditionalInformation(additionalInfo);

		// Save the Book entity
		Book savedBook = bookRepository.save(book);

		// Print IDs
		System.out.println("Book ID: " + savedBook.getId());
		System.out.println("Author ID: " + savedBook.getAuthor().getId());
		System.out.println("Category ID: " + savedBook.getCategory().getId());
		System.out.println("Additional Info ID: " + savedBook.getAdditionalInformation().getId());

		return savedBook;
	}

	// Adding List of Books
	public List<Book> saveBooks(List<BookDTO> bookDTOs) {
		List<Book> books = new ArrayList<>();

		for (BookDTO bookDTO : bookDTOs) {
			// Map BookDTO to Book entity
			Book book = new Book();
			book.setTitle(bookDTO.getTitle());
			book.setLanguage(bookDTO.getLanguage());
			book.setDescription(bookDTO.getDescription());
			book.setPrice(bookDTO.getPrice());

			// Handle Author dynamically
			Author author = authorRepository.findByName(bookDTO.getAuthorName()).orElseGet(() -> {
				Author newAuthor = new Author();
				newAuthor.setName(bookDTO.getAuthorName());
				return authorRepository.save(newAuthor);
			});
			book.setAuthor(author);

			// Handle Category dynamically
			BookCategory category = bookCategoryRespository.findByName(bookDTO.getCategoryName()).orElseGet(() -> {
				BookCategory newCategory = new BookCategory();
				newCategory.setName(bookDTO.getCategoryName());
				return bookCategoryRespository.save(newCategory);
			});
			book.setCategory(category);

			// Handle Additional Information dynamically

			if (bookDTO.getAdditionalInfomationDTO() != null) {
				AdditionalInfomationDTO additionalInfoDTO = bookDTO.getAdditionalInfomationDTO();

				AdditionalInformation additionalInfo = new AdditionalInformation();
				additionalInfo.setPublisher(additionalInfoDTO.getPublisher());
				additionalInfo.setLanguage(additionalInfoDTO.getLanguage());
				additionalInfo.setPublishingYear(additionalInfoDTO.getPublishingYear());
				additionalInfo.setTotalPages(additionalInfoDTO.getTotalPages());
				additionalInfo.setEdition(additionalInfoDTO.getEdition());
				additionalInfo.setBuyFromAmazonLink(additionalInfoDTO.getBuyFromAmazonLink());

				// Save Additional Information entity and set it in Book
				additionalInfo = additionalInfoRepo.save(additionalInfo);
				book.setAdditionalInformation(additionalInfo);
			}

			books.add(book);

		}
		// Save all books in a single transaction
		return bookRepository.saveAll(books);

	}

	// view list of books
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	// Add Books to Category
	public BookCategory addBooksToCategory(String CategoryName, List<BookDTO> bookDTOs) {
		// fetch category by name
		BookCategory bookCategory = bookCategoryRespository.findByName(CategoryName)
				.orElseThrow(() -> new RuntimeException("Category Not Found" + CategoryName));

		// Iterate through BookDTOs and Map to BookEntities
		for (BookDTO bookDTO : bookDTOs) {
			Book book = new Book();
			book.setTitle(bookDTO.getTitle());
			book.setLanguage(bookDTO.getLanguage());
			book.setPrice(bookDTO.getPrice());
			book.setCategory(bookCategory);

			// fetch author by name (Assuming provided in DTO)
			Author author = authorRepository.findByName(bookDTO.getAuthorName()).orElseGet(() -> {
				Author newAuthor = new Author();
				newAuthor.setName(bookDTO.getAuthorName());
				return authorRepository.save(newAuthor);
			});
			book.setAuthor(author);

			// Map Additional Information
			if (bookDTO.getAdditionalInfomationDTO() != null) {
				AdditionalInfomationDTO additionalInfoDTO = bookDTO.getAdditionalInfomationDTO();

				AdditionalInformation additionalInfo = new AdditionalInformation();
				additionalInfo.setPublisher(additionalInfoDTO.getPublisher());
				additionalInfo.setLanguage(additionalInfoDTO.getLanguage());
				additionalInfo.setPublishingYear(additionalInfoDTO.getPublishingYear());
				additionalInfo.setTotalPages(additionalInfoDTO.getTotalPages());
				additionalInfo.setEdition(additionalInfoDTO.getEdition());
				additionalInfo.setBuyFromAmazonLink(additionalInfoDTO.getBuyFromAmazonLink());

				// Save Additional Information entity and set it in Book
				additionalInfo = additionalInfoRepo.save(additionalInfo);
				book.setAdditionalInformation(additionalInfo);
			}
			// Add Book to the category
			bookCategory.getBooks().add(book);

		}
		// Save category(Cascade to books)
		return bookCategoryRespository.save(bookCategory);

	}
	
	//GetBooks By categoryName 
	 
		
		
		
		
		
		
	}
	
	
	
	
	
	




// Update Book
/*
 * public Book updateBook(Long id, BookDTO bookDTO) { Optional<Book>
 * optionalBook = bookRepository.findById(id); if (optionalBook.isPresent()) {
 * Book book = optionalBook.get(); book.setTitle(bookDTO.getTitle());
 * book.setLanguage(bookDTO.getLanguage());
 * book.setPopularity(bookDTO.getPopularity());
 * book.setProductsku(bookDTO.getProductsku());
 * book.setPrice(bookDTO.getPrice());
 * book.setDiscountPercentage(bookDTO.getDiscountPercentage());
 * book.setDescription(bookDTO.getDescription());
 * 
 * // Update category, author, and additional information BookCategory category
 * =
 * bookCategoryRepository.findById(bookDTO.getCategory().getId()).orElseThrow(()
 * -> new RuntimeException("Category not found")); Author author =
 * authorRepository.findById(bookDTO.getAuthor().getId()).orElseThrow(() -> new
 * RuntimeException("Author not found")); AdditionalInformation
 * additionalInformation =
 * additionalInformationRepository.findById(bookDTO.getAdditionalInformation().
 * getId()) .orElseThrow(() -> new
 * RuntimeException("Additional Information not found"));
 * 
 * book.setCategory(category); book.setAuthor(author);
 * book.setAdditionalInformation(additionalInformation); return
 * bookRepository.save(book); } return null; }
 */
