package com.shop.egs.controller;

import com.shop.egs.dto.CategoryDto;
import com.shop.egs.dto.ProductDto;
import com.shop.egs.model.Category;
import com.shop.egs.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CategoryController.ROUTE)
public class CategoryController {
    final static String ROUTE="/category";

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveCategory(@RequestBody CategoryDto dto) {
        return new ResponseEntity<>(service.saveCategory(dto.getCategoryName()).getName(), HttpStatus.OK);
    }
    @GetMapping(value = "/getall")
    public ResponseEntity<Object>getAllProducts(){
        return new ResponseEntity<>(service.getAllCategories(), HttpStatus.OK);
    }
}
