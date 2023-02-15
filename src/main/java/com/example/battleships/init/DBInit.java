package com.example.battleships.init;

import com.example.battleships.service.CategoryService;
import com.example.battleships.service.ShipService;
import com.example.battleships.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DBInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ShipService shipService;

    public DBInit(CategoryService categoryService, UserService userService, ShipService shipService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.shipService = shipService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.initCategoryDB();
        this.userService.initUsersDB();
        this.shipService.initShipDB();

    }
}
