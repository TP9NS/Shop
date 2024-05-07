package com.example.server.DTOREUQUEST;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
	Long reviewId;
	Long customer;
	Long product;
	String title;
	String content;
}
