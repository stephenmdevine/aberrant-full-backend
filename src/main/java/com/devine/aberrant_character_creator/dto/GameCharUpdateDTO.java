package com.devine.aberrant_character_creator.dto;

import com.devine.aberrant_character_creator.model.Flaw;
import com.devine.aberrant_character_creator.model.Merit;
import lombok.Data;

import java.util.List;

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
    private int expSpent;

    private int willpowerBonus;
    private int willpowerNova;
    private int quantumBonus;
    private int quantumNova;
    private int quantumPoolBonus;
    private int initiativeBonus;
    private int baseTaint;
    private int taint;

    private List<Flaw> flaws;
    private List<Merit> merits;

    private List<AttributeUpdateDTO.AttributeDTO> attributes;
    private List<AbilityUpdateDTO.AbilityDTO> abilities;
    private List<BackgroundUpdateDTO.BackgroundDTO> backgrounds;

}
