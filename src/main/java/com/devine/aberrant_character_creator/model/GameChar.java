package com.devine.aberrant_character_creator.model;

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
    @OneToMany(mappedBy = "gameChar")
    private List<Attribute> attributes = new ArrayList<>();
//    @OneToMany(mappedBy = "gameChar")
//    private List<AttributeSet> attributeSets = new ArrayList<>();

    public GameChar() {    }

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

}
