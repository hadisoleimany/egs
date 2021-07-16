package com.shop.egs.service;

import com.shop.egs.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @Test
    void saveCategory() {

    }

    @Test
    void deleteCategory() {
    }

    @org.junit.Test
    public void testSaveCategory() throws Exception {
        String ctg="food";
        Category category = service.saveCategory(ctg);
        assertEquals(ctg,category.getName(),"It's True");
    }
}