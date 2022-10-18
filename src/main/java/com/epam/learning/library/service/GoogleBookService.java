package com.epam.learning.library.service;

import java.util.List;

import com.epam.learning.library.dto.BookDTO;

public interface GoogleBookService {

	List<BookDTO> searchBookList(String searchText);
	
	BookDTO getSingleBook(String googleId);
}
