package com.example.battleships.repository;

import com.example.battleships.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ShipRepo extends JpaRepository<Ship, Long> {
    Set<Ship> findAllByUserId(long userId);

    Set<Ship> findAllByUserIdNot(long userId);
}
