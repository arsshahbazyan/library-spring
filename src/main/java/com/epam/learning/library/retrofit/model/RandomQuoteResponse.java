package com.epam.learning.library.retrofit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RandomQuoteResponse {

	private String quote;
	
	private String author;
}
