package com.nimap.infotech.productmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private Long categoryId;  
}
