package com.epam.learning.library.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.learning.library.entity.Isbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.learning.library.dao.BookDAO;
import com.epam.learning.library.dto.BookDTO;
import com.epam.learning.library.entity.Author;
import com.epam.learning.library.entity.Book;
import com.epam.learning.library.entity.BookCategory;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDAO bookDAO;
	
	@Override
	@Transactional
	public boolean isBookInLibrary(String googleId) {
		
		boolean bookInLibrary = true;
		
		if (bookDAO.getBookByGoogleId(googleId) == null) {
			bookInLibrary = false;
		};
		
		return bookInLibrary;
	}

	@Override
	@Transactional
	public List<BookDTO> searchBookList(String searchText, int pageNo, int resultsPerPage) {
		
		List<Book> bookEntitiesList = null;
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		
		bookEntitiesList = bookDAO.searchBookList(searchText, pageNo, resultsPerPage);
		
		if (bookEntitiesList != null) {
			for (Book bookEntity : bookEntitiesList) {
				BookDTO bookDTO = new BookDTO();
				bookDTO = this.convertBookEntityToBookDTO(bookEntity);
				bookDTOList.add(bookDTO);
			}
		}
	
		return bookDTOList;
	}
	
	@Override
	@Transactional
	public int searchBooksResultsCount(String searchText) {
		
		int bookResultsCount = 0;
		bookResultsCount = bookDAO.searchBookResultsCount(searchText);
		
		return bookResultsCount;
	}

	@Override
	public int searchBookPagesCount(String searchText, int resultsPerPage) {
		
		int bookCount = 0;
		int searchBookPagesCount = 1;
		bookCount = this.searchBooksResultsCount(searchText);
		searchBookPagesCount = (int) Math.floorDiv(bookCount, resultsPerPage) + 1;
		
		return searchBookPagesCount;
	}

	@Override
	public BookDTO getBookDTOById(int id) {
		
		BookDTO bookDTO  = null;
		Book book = null;
		
		book = bookDAO.getBookById(id);
		bookDTO = this.convertBookEntityToBookDTO(book);
		
		return bookDTO;
	}

	@Override
	@Transactional
	public Book saveBook(BookDTO bookDTO, String changedByUsername) {
		
		Book book = null;
		book = this.convertBookDTOToBookEntity(bookDTO);
		book = bookDAO.saveBook(book, changedByUsername);
		
		return book;
	}

	private Book convertBookDTOToBookEntity(BookDTO bookDTO) {
		
		Book book = new Book();
		Set<Author> authors = null;
		Set<BookCategory> categories = null;
		Isbn isbn = new Isbn();
		
		authors = this.getAuthorsByName(bookDTO.getAuthors());
		categories = this.getBookCategoriesByName(bookDTO.getBookCategories());
		isbn.setIsbn10(bookDTO.getIsbn_10());
		isbn.setIsbn13(bookDTO.getIsbn_13());
		
		book.setGoogleId(bookDTO.getGoogleId());
		book.setTitle(bookDTO.getTitle());
		book.setAuthors(authors);
		book.setPublisher(bookDTO.getPublisher());
		book.setPublishedDate(bookDTO.getPublishedDate());
		book.setIsbn(isbn);
		book.setPageCount(bookDTO.getPageCount());
		book.setCategories(categories);
		book.setRating(bookDTO.getRating());
		book.setImageLink(bookDTO.getImageLink());
		book.setDescription(bookDTO.getDescription());
	
		return book;		
	}
	
	private BookDTO convertBookEntityToBookDTO(Book bookEntity) {
		
		
		BookDTO bookDTO = new BookDTO();
		List<String> authors = new ArrayList<String>();
		List<String> categories = new ArrayList<String>();
		
		bookDTO.setId(bookEntity.getId());
		bookDTO.setGoogleId(bookEntity.getGoogleId());
		bookDTO.setTitle(bookEntity.getTitle());
		
		for (Author author : bookEntity.getAuthors()) {
			authors.add(author.getName());
		}
		bookDTO.setAuthors(authors);
		
		bookDTO.setPublisher(bookEntity.getPublisher());
		bookDTO.setPublishedDate(bookEntity.getPublishedDate());
		bookDTO.setIsbn_10(bookEntity.getIsbn().getIsbn10());
		bookDTO.setIsbn_13(bookEntity.getIsbn().getIsbn13());
		bookDTO.setPageCount(bookEntity.getPageCount());
		
		for (BookCategory category: bookEntity.getCategories()) {
			categories.add(category.getName());
		}
		bookDTO.setBookCategories(categories);
		
		bookDTO.setRating(bookEntity.getRating());
		bookDTO.setImageLink(bookEntity.getImageLink());
		bookDTO.setDescription(bookEntity.getDescription());

		bookDTO.setStatus(this.checkBookStatus(bookEntity.getId()));
		return bookDTO;
	}

	private Set<Author> getAuthorsByName(List<String> authorsListString){
		
		Set<Author> authors = new HashSet<Author>();
		
		for (String authorName : authorsListString) {
			Author author = null;
			author = bookDAO.getAuthorByName(authorName);
			
			if(author == null) {
				author = new Author();
				author.setName(authorName);
			} 
			authors.add(author);
		}
		return authors;
	}
	
	private Set<BookCategory> getBookCategoriesByName(List<String> bookCategoriesListString){
		
		Set<BookCategory> categories = new HashSet<BookCategory>();
		
		for(String categoryName : bookCategoriesListString) {
			BookCategory category = null;
			category = bookDAO.getBookCategoryByName(categoryName);
			
			if(category == null) {
				category = new BookCategory();
				category.setName(categoryName);
			}
			categories.add(category);
		}
		
		return categories;
	}
	
	@Transactional
	private String checkBookStatus(int id) {
		
		String status = null;
		
		if (bookDAO.isBookReserved(id)) {
			status = "RESERVED";
		} else if (bookDAO.isBookBorrowed(id)) {
			status = "BORROWED";
		} else {
			status = "AVAILABLE";
		}
		return status;
	}

}
