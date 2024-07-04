package com.devine.aberrant_character_creator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GameChar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
