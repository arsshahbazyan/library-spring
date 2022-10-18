package com.epam.learning.library.dao;

import java.util.List;
import java.util.Map;

import com.epam.learning.library.entity.Role;
import com.epam.learning.library.entity.User;
import com.epam.learning.library.entity.UserLog;
import com.epam.learning.library.entity.UserPasswordToken;

public interface UserDAO {

	User getActiveUser(String username);
	
	User getActiveUserByEmail(String email);
	
	User getUserById(int id);
	
	User saveUser(User user, String changedByUsername);
	
	User updateUser(int id, Map<String, String> changedFields, String changedByUsername);
	
	void updatePassword(User user);
	
	User enableUser(int id, String changedByUsername);
	
	User disableUser(int id, String changedByUsername);
	
	Role getRoleByName(String roleName);
	
	List<User> getAllUsers();
	
	List<User> searchUsers(String searchText, int pageNo, int resultsPerPage);
	
	int searchUsersTotalCount(String searchText);
	
	List<UserLog> getUserLogs(int id);
	
	UserPasswordToken createPasswordResetToken(UserPasswordToken userPasswordToken);
	
	UserPasswordToken updateResetPasswordTokenForUser(UserPasswordToken userPasswordToken);
	
	void deleteUserPassword(int userId);
	
	UserPasswordToken getUserPasswordResetTokenByUserId(int userId);
	
	void penaltiesPaidForUser(int userId);
	
}
