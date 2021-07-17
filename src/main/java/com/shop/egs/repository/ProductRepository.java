package com.shop.egs.repository;

import com.shop.egs.model.Category;
import com.shop.egs.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Optional<Product> findByProductName(String productName);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByProductNameContains(String productName);
}
