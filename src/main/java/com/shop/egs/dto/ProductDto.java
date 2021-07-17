package com.shop.egs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private CategoryDto categoryDto;
    private String productName;
    private BigDecimal price;
    private String description;
}
