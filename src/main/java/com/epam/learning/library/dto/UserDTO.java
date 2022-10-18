package com.epam.learning.library.dto;

import java.util.Date;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.epam.learning.library.validation.EmailValid;
import com.epam.learning.library.validation.FieldMatch;
import com.epam.learning.library.validation.PasswordValid;
import com.epam.learning.library.validation.PhoneNumber;
import com.epam.learning.library.validation.PostalCode;
import com.epam.learning.library.validation.UniqueEmail;
import com.epam.learning.library.validation.UniqueUsername;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString()
@NoArgsConstructor
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match!")
})
public class UserDTO {

	@UniqueUsername
	@NotEmpty
	@Size(min=5,max=45)
	private String username;
	
	@NotEmpty
	@Size(min=2,max=60)
	private String firstName;
	
	@NotEmpty
	@Size(min=2,max=60)
	private String lastName;
	
	@PasswordValid
	@NotEmpty
	private String password;
	
	@PasswordValid
	@NotEmpty
	private String confirmPassword;
	
	@UniqueEmail
	@NotEmpty
	@EmailValid
	private String email;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past
	@NotNull
	private Date birthday;
	
	@NotEmpty
	@PhoneNumber
	private String phone;
	
	@NotEmpty
	@Size(min=2,max=60)
	private String address;
	
	@NotEmpty
	@PostalCode
	private String postalCode;

	@NotEmpty
	@Size(min=2,max=60)
	private String city;
	
	private String role;
	
	private boolean enable;
	
	private PenaltyDTO penalty;
	
	public UserDTO(	String username, String firstName, String lastName,
					String password, String confirmPassword, String email) {
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}
}
