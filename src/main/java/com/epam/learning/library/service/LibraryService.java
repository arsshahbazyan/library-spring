package com.epam.learning.library.service;

import java.math.BigDecimal;
import java.util.List;

import com.epam.learning.library.dto.BookDTO;
import com.epam.learning.library.dto.LibraryLogDTO;
import com.epam.learning.library.dto.ManageDTO;
import com.epam.learning.library.dto.PenaltyDTO;

public interface LibraryService {

	boolean isUserExceedBooksLimit(int id);
	
	int allManageResultsCount();
	
	int searchManageResultsCount(String searchText, int typeNo, int statusNo);
	
	int calculateManagePagesCount(int resultsCount, int resultsPerPage);
	
	List<ManageDTO> getManageListByUser(int userId);
	
	List<ManageDTO> allManageList(int pageNo, int resultsPerPage);

	List<ManageDTO> searchManageList(String searchText, int typeNo, int statusNo, int pageNo, int resultsPerPage);
	
	BookDTO reserveBook(int id, int userId);
	
	void borrowBook(int bookId, int librarianId);
	
	void returnBook(int bookId, int librarianId);
	
	List<ManageDTO> sortManageList(List<ManageDTO> manageDTO, String sortBy);
	
	List<LibraryLogDTO> getLibraryLogsByUser(int userId);
	
	List<LibraryLogDTO> getLibraryLogsByBook(int bookId);
	
	List<PenaltyDTO> getPenaltiesByUser(int userId);
	
	BigDecimal calculatePenalty(int days);
	
	BigDecimal sumPenalties(List<PenaltyDTO> penalties);
	
	boolean makePayment(int userId);
}
