package com.example.digishopapi.services;

import com.example.digishopapi.dtos.CategoryDto;
import com.example.digishopapi.exceptions.FoundException;
import com.example.digishopapi.exceptions.NotFoundException;
import com.example.digishopapi.models.Category;
import com.example.digishopapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(CategoryDto categoryDto) {
        Optional<Category> foundCategory = categoryRepository.findByName(categoryDto.getName().toLowerCase());
        if (foundCategory.isPresent()) {
            throw new FoundException("Category already exist");

        } else {
            Category category = new Category();
            category.setCreatedAt(new Date(System.currentTimeMillis()));
            category.setImage(categoryDto.getImage());
            category.setUpdatedAt(new Date(System.currentTimeMillis()));
            category.setName(categoryDto.getName().toLowerCase());
            return categoryRepository.save(category);

        }
    }

    public Category findCategoryById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with provided id doesnot exist");
        } else {
            return category.get();
        }
    }

    public List<Category> findAllCategories(String pageNumber) {
        if (pageNumber == null) {
            return findCategories();
        } else {
            return getPaginatedCategories(Integer.parseInt(pageNumber));
        }
    }

    private List<Category> findCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        if (categories.size() == 0) {
            return new ArrayList<>();
        } else {
            return categories;
        }
    }


    private List<Category> getPaginatedCategories(int pageNumber) {
        List<Category> categories = categoryRepository.findAll(PageRequest.of(pageNumber, 10).withSort(Sort.by(Sort.Direction.DESC, "createdAt"))).toList();
        if (categories.size() == 0) {
            return new ArrayList<>();
        } else {
            return categories;
        }
    }


    public void deleteCategory(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with provided id doesnot exist");
        } else {
            categoryRepository.deleteById(id);
        }
    }


}
