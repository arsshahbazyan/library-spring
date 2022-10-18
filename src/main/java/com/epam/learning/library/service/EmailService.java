package com.epam.learning.library.service;

import com.epam.learning.library.entity.User;

public interface EmailService {
	
	void sendUserRegistrationConfirmEmail(User user);
	
	void sendForgotPasswordEmail(User user, String path, String token);

}
