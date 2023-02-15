package com.example.battleships.model.dto;

import com.example.battleships.model.enums.CategoryEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ShipDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Power is required")
    @Min(value = 0)
    private Integer power;

    @NotNull(message = "Health is required")
    @Min(value = 0)
    private Integer health;

    @NotNull(message = "Created is required")
    @PastOrPresent
    private LocalDate created;

    @NotNull(message = "Category is required")
    private String category;

    public ShipDTO() {
    }

    public String getName() {
        return name;
    }

    public ShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipDTO setPower(Integer power) {
        this.power = power;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipDTO setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ShipDTO setCategory(String category) {
        this.category = category;
        return this;
    }
}
