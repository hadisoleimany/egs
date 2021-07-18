package com.shop.egs.controller;

import com.shop.egs.dto.ProductDto;
import com.shop.egs.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(ProductController.ROUTE)
public class ProductController {
    final static String ROUTE="/product";
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping("/getall")
    public List<ProductDto> getAll(){
        return  service.getAll();
    }
    @PostMapping("/save")
    public ProductDto save(@RequestBody ProductDto dto){
        return service.saveProduct(dto);
    }
    @PostMapping("/edit")
    public ProductDto update(@RequestBody ProductDto dto) {
        return service.update(dto);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody ProductDto dto) {
        service.deleteProduct(dto);
    }
    @GetMapping("/getallbycategory")
    public ResponseEntity<Object> getAllProductWithCategory(@RequestParam(name = "categoryname")String categoryName){
        return new ResponseEntity<>(service.findByCategory(categoryName), HttpStatus.OK);
    }
    @GetMapping("/getallbyname")
    public ResponseEntity<Object> getAllProductByName(@RequestParam(name = "productname")String productName){
        return new ResponseEntity<>(service.findAllByProductName(productName), HttpStatus.OK);
    }
    @GetMapping("/getallbyprice")
    public ResponseEntity<Object> findByPriceRange(@RequestParam(name = "min")Long min,@RequestParam(name = "max")Long max){
        return new ResponseEntity<>(service.findByPriceRange(new BigDecimal(min),new BigDecimal(max)), HttpStatus.OK);
    }
}
