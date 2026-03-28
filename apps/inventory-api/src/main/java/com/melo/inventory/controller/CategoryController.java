package com.melo.inventory.controller;

import com.melo.inventory.model.Category;
import com.melo.inventory.model.CategoryRequest;
import com.melo.inventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){this.categoryService = categoryService;}

    @PostMapping("/categories")
    public ResponseEntity<Category> categoryRequest(@Valid @RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest));
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable){
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }
}
