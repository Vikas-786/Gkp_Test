package com.gkp.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDTO {
    private Long id;
    private String title;
    private String language;
    private Integer popularity;
    private String productsku;
    private Double price;
    private Double discountPercentage;
    private Double finalPrice;
    private String description;
    private String categoryName; // Avoid circular reference by using DTO
	private String authorName; // Avoid circular reference by using DTO 
	private AdditionalInfomationDTO additionalInfomationDTO; // Avoid circular reference by using DTO
	 
   }
