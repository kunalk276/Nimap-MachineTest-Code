package com.nimap.infotech.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor
public class ProductJoin {
	
	   private int productId;
	   
	   private String productName;
	   
	   private int categoryId;
	   
	   private String categoryName;

}