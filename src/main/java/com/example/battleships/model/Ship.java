package com.example.battleships.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @Size(min = 2,max = 10)
    private String name;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer health;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer power;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public Ship() {
    }

    public Long getId() {
        return id;
    }

    public Ship setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ship setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public Ship setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public Ship setPower(Integer power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Ship setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Ship setCategory(Category category) {
        this.category = category;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Ship setUser(User user) {
        this.user = user;
        return this;
    }
}
