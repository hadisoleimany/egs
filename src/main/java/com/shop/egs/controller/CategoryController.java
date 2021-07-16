package com.shop.egs.controller;

import com.shop.egs.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CategoryController.ROUTE)
public class CategoryController {
    final static String ROUTE="/category";

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/add")
    public ResponseEntity<Object> sayHello(@RequestParam(name = "categoryname") String name) throws Exception {
        return new ResponseEntity<>(service.saveCategory(name).getName(), HttpStatus.OK);
    }
    @GetMapping(value = "/getall")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(service.getAllCategories(), HttpStatus.OK);
    }
}
