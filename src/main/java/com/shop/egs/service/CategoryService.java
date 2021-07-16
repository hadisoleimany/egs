package com.shop.egs.service;

import com.shop.egs.model.Category;
import com.shop.egs.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepository rp;

    public CategoryService(CategoryRepository rp) {
        this.rp = rp;
    }

    public Category saveCategory(String name) throws Exception {
        if(checkExist(name).isPresent()){
            throw new Exception("The Name Already Exist");
        }
        return rp.save(new Category(name));
    }
    public void deleteCategory(Category category){
        rp.delete(category);
    }
    public Set<Category> getAllCategories(){
        Set<Category> categories=new HashSet<>();
        rp.findAll().forEach(categories::add);
        return categories;
    }
    public Optional<Category> checkExist(String name){
        return rp.findByName(name);
    }
}
