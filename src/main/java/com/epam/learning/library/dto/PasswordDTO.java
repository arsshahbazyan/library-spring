package com.epam.learning.library.dto;

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
public class PasswordDTO {

	private String password;
	
	private String confirmPassword;
}
