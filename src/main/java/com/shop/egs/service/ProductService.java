package com.shop.egs.service;

import com.shop.egs.BusinessException;
import com.shop.egs.dto.CategoryDto;
import com.shop.egs.dto.ProductDto;
import com.shop.egs.model.Category;
import com.shop.egs.model.Product;
import com.shop.egs.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository rp;
    private final CategoryService categoryService;

    public ProductService(ProductRepository rp, CategoryService categoryService) {
        this.rp = rp;
        this.categoryService = categoryService;
    }

    //validations for insert
    public ProductDto saveProduct(ProductDto dto) throws Exception {
        Product product = rp.save(convertToProduct(dto));
        return convertToProductDto(product);
    }

    //validations for delete
    public void deleteProduct(ProductDto dto) throws Exception {
        Product product = checkExist(dto);
        rp.delete(product);
    }

    public List<ProductDto> getAll() {
        List<ProductDto> dtoList = new ArrayList<>();
        rp.findAll().forEach(c -> dtoList.add(convertToProductDto(c)));
        return dtoList;
    }

    //validations for update
    public ProductDto update(ProductDto dto) throws Exception {
        Product product = checkExist(dto);
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return convertToProductDto(rp.save(product));

    }

    private Product checkExist(ProductDto dto) {
        Optional<Product> product = rp.findByProductName(dto.getProductName());

        if (product.isEmpty()) {
            throw new BusinessException(dto.getProductName()+" Product NotFound !!!");
        }
        return product.get();
    }

    private Product convertToProduct(ProductDto dto) {
        Category category = categoryService.getCategoryByName(dto.getCategoryDto().getCategoryName());

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        return product;
    }

    private ProductDto convertToProductDto(Product product) {
        return new ProductDto(new CategoryDto(product.getCategory().getName()),
                product.getProductName(), product.getPrice(), product.getDescription());
    }

    public List<ProductDto> findByCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return convertToListProductDto(rp.findAllByCategory(category));

    }

    private List<ProductDto> convertToListProductDto(List<Product> productList) {
        if(productList==null || productList.size()==0){
            return new ArrayList<>();
        }
        return productList.stream().map(c -> new ProductDto(new CategoryDto(c.getCategory().getName()),
                c.getProductName(), c.getPrice(), c.getDescription())).collect(Collectors.toList());
    }

    public List<ProductDto>  findAllByProductName(String productName) {
        return convertToListProductDto(rp.findAllByProductNameContains(productName));
    }
    public Product findByProductName(String productName){
        ProductDto dto = new ProductDto();
        dto.setProductName(productName);
        return checkExist(dto);
    }
}
