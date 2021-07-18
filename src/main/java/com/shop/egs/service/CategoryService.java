package com.shop.egs.service;

import com.shop.egs.aop.AccessibleUser;
import com.shop.egs.exception.BusinessException;
import com.shop.egs.dto.CategoryDto;
import com.shop.egs.model.Category;
import com.shop.egs.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository rp;

    public CategoryService(CategoryRepository rp) {
        this.rp = rp;
    }

    @AccessibleUser
    public Category saveCategory(String name){
        checkExist(name);
        return rp.save(new Category(name));
    }
    @AccessibleUser
    public void deleteCategory(Category category){
        rp.delete(category);
    }
    public List<CategoryDto> getAllCategories(){
        List<CategoryDto> categories=new ArrayList<>();
        rp.findAll().forEach(c->categories.add(new CategoryDto(c.getName())));
        return categories;
    }
    private void checkExist(String name) {
        if( rp.findByName(name).isPresent()){
            throw new BusinessException("The Name Already Exist");
        }
    }
    public Category getCategoryByName(String name){
        Optional<Category> category = rp.findByName(name);
        if(category.isEmpty()){
            throw new BusinessException("Category Not Found");
        }
        return category.get();
    }
}
