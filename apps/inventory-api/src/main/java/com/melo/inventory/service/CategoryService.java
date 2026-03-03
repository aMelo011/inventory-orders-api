package com.melo.inventory.service;

import com.melo.inventory.model.Category;
import com.melo.inventory.model.CategoryRequest;
import com.melo.inventory.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService (CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}

    public Category createCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){return categoryRepository.findAll();}
}
