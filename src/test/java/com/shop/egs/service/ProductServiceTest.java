package com.shop.egs.service;

import com.shop.egs.dto.CategoryDto;
import com.shop.egs.dto.ProductDto;
import com.shop.egs.exception.BusinessException;
import com.shop.egs.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Test
    void findByPriceRangeExpectBusinessException() {
        assertThrows(BusinessException.class, () -> {
            productService.findByPriceRange(new BigDecimal(100), new BigDecimal(50));
        });
    }

    @Test
    void saveProductExpectBusinessException() {
        ProductDto dto = new ProductDto();
        dto.setProductName("shoes");
        dto.setDescription("A pair Of Shoes");
        dto.setPrice(new BigDecimal(1400));
        dto.setCategoryDto(new CategoryDto("Hadi"));
        productService.saveProduct(new ProductDto());
        assertThrows(BusinessException.class, () -> {
            productService.saveProduct(dto);
        },"Product Already Exist!!");
    }
}