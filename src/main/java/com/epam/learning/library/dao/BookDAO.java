package com.epam.learning.library.dao;

import java.util.List;

import com.epam.learning.library.entity.Author;
import com.epam.learning.library.entity.Book;
import com.epam.learning.library.entity.BookCategory;
import com.epam.learning.library.entity.Borrowed;
import com.epam.learning.library.entity.LibraryLog;
import com.epam.learning.library.entity.BookPenalty;
import com.epam.learning.library.entity.Reserved;

public interface BookDAO {

	Book getBookByGoogleId(String googleId);
	
	Author getAuthorByName(String authorName);
	
	BookCategory getBookCategoryByName(String categoryName);
	
	List<Book> searchBookList(String searchText, int pageNo, int resultsPerPage);
	
	int searchBookResultsCount(String searchText);
	
	Book getBookById(int id);
	
	Book saveBook(Book book, String changedByUsername);

	Book reserveBook(int bookId, int userId, int days);
	
	void borrowBook(int bookId, int userId, int days);
	
	void returnBook(int bookId);
	
	boolean isBookReserved(int id);
	
	boolean isBookBorrowed(int id);
	
	int getReservedBooksTotalCountByUser(int userId);
	
	int getBorrowedBooksTotalCountByUser(int userId);
	
	int getReservedBooksTotalCountByBook(int bookId);
	
	int getBorrowedBooksTotalCountByBook(int bookId);
	
	int getReservedBooksTotalCount();
	
	int getBorrowedBooksTotalCount();
	
	List<Reserved> getAllReservedBooksList();
	
	List<Borrowed> getAllBorrowedBooksList();
	
	List<Reserved> getReservedBooksListByUserId(int userId);
	
	List<Borrowed> getBorrowedBooksListByUserId(int userId);
	
	List<Reserved> getReservedBooksListByBookId(int bookId);
	
	List<Borrowed> getBorrowedBooksListByBookId(int bookId);
	
	Borrowed getBorrowedBookByBookId(int bookId);
	
	List<LibraryLog> getLibraryLogsByUser(int userId);
	
	List<LibraryLog> getLibraryLogsByBook(int bookId);
	
	List<BookPenalty> getBookPenaltiesByUser(int userId);
	
	BookPenalty getPenaltyForBook(int bookId);
	
	void setReturnDateForPenalty(int bookId);
}
