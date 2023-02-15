package com.example.battleships.repository;

import com.example.battleships.model.Category;
import com.example.battleships.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(CategoryEnum categoryEnum);
}
