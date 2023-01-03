package com.example.digishopapi.controllers;

import com.example.digishopapi.dtos.CategoryDto;
import com.example.digishopapi.models.Category;
import com.example.digishopapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity<Category> createCategory(@RequestBody @Validated  CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id ){
        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Category>>getAllCategories(){
        List<Category> categories=categoryService.findAllCategories();
        return new ResponseEntity<>(categories, categories.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
