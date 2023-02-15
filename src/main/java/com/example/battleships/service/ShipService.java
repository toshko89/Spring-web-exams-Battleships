package com.example.battleships.service;

import com.example.battleships.model.Category;
import com.example.battleships.model.Ship;
import com.example.battleships.model.User;
import com.example.battleships.model.dto.FightDTO;
import com.example.battleships.model.dto.ShipDTO;
import com.example.battleships.model.enums.CategoryEnum;
import com.example.battleships.repository.ShipRepo;
import com.example.battleships.session.LoggedUserSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ShipService {

    private final ShipRepo shipRepo;
    private final UserService userService;
    private final CategoryService categoryService;
    private final LoggedUserSession loggedUserSession;

    public ShipService(ShipRepo shipRepo, UserService userService, CategoryService categoryService, LoggedUserSession loggedUserSession) {
        this.shipRepo = shipRepo;
        this.userService = userService;
        this.categoryService = categoryService;
        this.loggedUserSession = loggedUserSession;
    }

    public void initShipDB() {
        if (shipRepo.count() == 0) {
            Ship ship1 = new Ship()
                    .setCategory(categoryService.findCategoryByName(CategoryEnum.BATTLE))
                    .setCreated(LocalDate.now())
                    .setName("Bismarck")
                    .setPower(100)
                    .setHealth(100)
                    .setUser(userService.findUserByUsername("Nqma"));
            this.shipRepo.save(ship1);

            Ship ship2 = new Ship()
                    .setCategory(categoryService.findCategoryByName(CategoryEnum.PATROL))
                    .setCreated(LocalDate.now())
                    .setName("Enterprise")
                    .setPower(50)
                    .setHealth(50)
                    .setUser(userService.findUserByUsername("Nqkoi si"));
            this.shipRepo.save(ship2);

            Ship ship3 = new Ship()
                    .setCategory(categoryService.findCategoryByName(CategoryEnum.CARGO))
                    .setCreated(LocalDate.now())
                    .setName("Cargo")
                    .setPower(25)
                    .setHealth(25)
                    .setUser(userService.findUserByUsername("Nishto"));
            this.shipRepo.save(ship3);
        }
    }

    public void addShip(ShipDTO shipDTO) {
        long userId = this.loggedUserSession.getId();

        User user = userService.getUserById(userId);

        Category category = categoryService.findCategoryByName(CategoryEnum.valueOf(shipDTO.getCategory()));

        Ship ship = new Ship().setCategory(category)
                .setCreated(LocalDate.now())
                .setName(shipDTO.getName())
                .setPower(shipDTO.getPower())
                .setHealth(shipDTO.getHealth())
                .setUser(user);

        this.shipRepo.save(ship);
    }

    public Set<Ship> findOwnShips(long userId) {
        return this.shipRepo.findAllByUserId(userId);
    }

    public Set<Ship> findOtherShips(long userId) {
        return this.shipRepo.findAllByUserIdNot(userId);
    }

    public List<Ship> findAllShips() {
        return this.shipRepo.findAll();
    }

    public void attack(FightDTO fightDTO) {
        Ship attacker = this.shipRepo.findById(fightDTO.getAttackerId()).orElse(null);
        Ship defender = this.shipRepo.findById(fightDTO.getDefenderId()).orElse(null);

        if (attacker != null && defender != null) {
            defender.setHealth(defender.getHealth() - attacker.getPower());
            if (defender.getHealth() <= 0) {
                this.shipRepo.delete(defender);
            } else {
                this.shipRepo.save(defender);
            }
        }
    }
}
