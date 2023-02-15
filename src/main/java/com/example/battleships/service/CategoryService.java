package com.example.battleships.service;

import com.example.battleships.model.Category;
import com.example.battleships.model.Ship;
import com.example.battleships.model.enums.CategoryEnum;
import com.example.battleships.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void initCategoryDB() {
        if(categoryRepo.count() == 0){
            List<Category> categoryList = Arrays.stream(CategoryEnum.values())
                    .map(categoryEnum -> new Category().setName(categoryEnum))
                    .toList();
            this.categoryRepo.saveAll(categoryList);
        }
    }

    public Category findCategoryByName(CategoryEnum categoryEnum) {
        return this.categoryRepo.findByName(categoryEnum);
    }
}
