package com.melo.inventory.service;

import com.melo.inventory.model.Category;
import com.melo.inventory.model.CategoryRequest;
import com.melo.inventory.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void shouldCreateCategorySuccessfully(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        CategoryRequest categoryRequest = new CategoryRequest("Electronics");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.createCategory(categoryRequest);
        assertEquals(1L, result.getId());
    }

    @Test
    public void shouldReturnAllCategories(){
        Category category1 = new Category();
        Category category2 = new Category();

        category1.setId(1L);
        category1.setName("Electronics");

        category2.setId(2L);
        category2.setName("Monitors");

        when(categoryRepository.findAll()).thenReturn(List.of(category1,category2));

        assertEquals(2, categoryService.getAllCategories().size());
    }

}
