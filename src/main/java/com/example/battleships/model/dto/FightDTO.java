package com.example.battleships.model.dto;

public class FightDTO {
    private Long attackerId = 0L;
    private Long defenderId = 0L;

    public FightDTO() {
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public FightDTO setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
        return this;
    }

    public Long getDefenderId() {
        return defenderId;
    }

    public FightDTO setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
        return this;
    }
}
