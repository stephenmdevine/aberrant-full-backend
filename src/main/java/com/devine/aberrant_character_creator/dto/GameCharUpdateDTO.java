package com.devine.aberrant_character_creator.dto;

import lombok.Data;

@Data
public class GameCharUpdateDTO {

    private String player;
    private String name;
    private String novaName;

    private String concept;
    private String nature;
    private String allegiance;
    private String description;

    private int attributePoints;
    private int abilityPoints;
    private int backgroundPoints;
    private int bonusPoints;
    private int novaPoints;
    private int experiencePoints;

}
