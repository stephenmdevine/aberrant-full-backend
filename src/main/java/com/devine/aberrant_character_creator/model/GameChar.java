package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class GameChar {

//    Name and ID fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String player;
    private String name;
    private String novaName;

//    User-defined flavor
    private String concept;
    private String nature;
    private String allegiance;
    private String description;

//    Point fields for character creation expenditure
    private int attributePoints;
    private int abilityPoints;
    private int backgroundPoints;
    private int bonusPoints;
    private int novaPoints;
    private int experiencePoints;

//    Relational link to other tables
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Attribute> attributes = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ability> abilities = new ArrayList<>();

    public GameChar() {
        initializeAttributes();
        initializeAbilities();
    }

    private void initializeAttributes() {
        String[] attributeNames = {
                "Strength", "Dexterity", "Stamina",
                "Perception", "Intelligence", "Wits",
                "Appearance", "Manipulation", "Charisma"};
        for (String attributeName : attributeNames) {
            Attribute attribute = new Attribute();
            attribute.setName(attributeName);
            attribute.setValue(1);
            attribute.setGameChar(this);
            attributes.add(attribute);
        }
    }

    private void initializeAbilities() {
        String[] abilityNames = {
                "Brawl", "Might", "Throwing",
                "Archery", "Athletics", "Drive", "Firearms", "Gunnery", "Heavy Weapons", "Legerdemain",
                    "Martial Arts", "Melee", "Pilot", "Ride", "Stealth",
                "Endurance", "Resistance",
                "Artillery", "Awareness", "Investigation", "Navigation",
                "Academics", "Analysis", "Bureaucracy", "Computer", "Demolitions", "Engineering", "Gambling",
                    "Intrusion", "Linguistics", "Medicine", "Occult", "Science", "Survival", "Tradecraft",
                "Arts", "Biz", "Meditation", "Rapport", "Shadowing", "Tactics", "Weave",
                "Disguise", "Intimidation", "Style",
                "Diplomacy", "Hypnosis", "Interrogation", "Seduction", "Streetwise", "Subterfuge",
                "Animal Training", "Carousing", "Command", "Etiquette", "Instruction", "Perform"
        };
        for (String abilityName : abilityNames) {
            Ability ability = new Ability();
            ability.setName(abilityName);
            ability.setValue(0);
            ability.setGameChar(this);
            abilities.add(ability);
        }
    }

//    Method to retrieve the value of a specific attribute by name
    public int getAttributeValue(String attributeName) {
        for (Attribute attribute : attributes) {
            if (attribute.getName().equalsIgnoreCase(attributeName)) {
                return attribute.getValue();
            }
        }
//        Return a default value or handle the case when the attribute is not found
        return 0;
    }

//    Method to retrieve the value of a specific ability by name
    public int getAbilityValue(String abilityName) {
        for (Ability ability : abilities) {
            if (ability.getName().equalsIgnoreCase(abilityName)) {
                return ability.getValue();
            }
        }
//        Return a default value or handle the case when the ability is not found
        return 0;
    }

}
