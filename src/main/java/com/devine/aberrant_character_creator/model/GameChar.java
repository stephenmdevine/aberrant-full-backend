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

//    Additional Character stats
    private int willpowerBonus = 0;  // Base is 3
    private int willpowerNova = 0;
    private int quantumBonus = 0;    // Base is 1
    private int quantumNova = 0;
    private int quantumPoolBonus = 0;   // Base is 20
    private int initiativeBonus = 0;
    private int baseTaint = 0;
    private int taint = 0;

//    Point fields for character creation expenditure
    private int attributePoints;
    private int abilityPoints;
    private int backgroundPoints;
    private int bonusPoints;
    private int novaPoints;
    private int experiencePoints;
    private int expSpent = 0;

//    Relational link to other tables
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("attribute-gameChar")
    private List<Attribute> attributes = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("ability-gameChar")
    private List<Ability> abilities = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("background-gameChar")
    private List<Background> backgrounds = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("megaAttribute-gameChar")
    private List<MegaAttribute> megaAttributes = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("flaw-gameChar")
    private List<Flaw> flaws = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("merit-gameChar")
    private List<Merit> merits = new ArrayList<>();
    @OneToMany(mappedBy = "gameChar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("power-gameChar")
    private List<Power> powers = new ArrayList<>();

    public GameChar() {
        initializeAttributes();
        initializeAbilities();
        initializeBackgrounds();
        initializeMegaAttributes();
    }

    private void initializeAttributes() {
        String[] attributeNames = {
                "Strength", "Dexterity", "Stamina",
                "Perception", "Intelligence", "Wits",
                "Appearance", "Manipulation", "Charisma", "No Attribute"};
        for (String attributeName : attributeNames) {
            Attribute attribute = new Attribute();
            attribute.setName(attributeName);
            attribute.setValue(1);
            if (attributeName.equals("No Attribute")) {attribute.setValue(0);}
            attribute.setGameChar(this);
            attributes.add(attribute);
        }
    }

    private void initializeAbilities() {
        String[][] abilityData = {
                {"Brawl", "Strength"}, {"Might", "Strength"}, {"Throwing", "Strength"},
                {"Archery", "Dexterity"}, {"Athletics", "Dexterity"}, {"Drive", "Dexterity"},
                {"Firearms", "Dexterity"}, {"Gunnery", "Dexterity"}, {"Heavy Weapons", "Dexterity"},
                {"Legerdemain", "Dexterity"}, {"Martial Arts", "Dexterity"}, {"Melee", "Dexterity"},
                {"Pilot", "Dexterity"}, {"Ride", "Dexterity"}, {"Stealth", "Dexterity"},
                {"Endurance", "Stamina"}, {"Resistance", "Stamina"},
                {"Artillery", "Perception"}, {"Awareness", "Perception"}, {"Investigation", "Perception"},
                {"Navigation", "Perception"},
                {"Academics", "Intelligence"}, {"Analysis", "Intelligence"}, {"Bureaucracy", "Intelligence"},
                {"Computer", "Intelligence"}, {"Demolitions", "Intelligence"}, {"Engineering", "Intelligence"},
                {"Gambling", "Intelligence"}, {"Intrusion", "Intelligence"}, {"Linguistics", "Intelligence"},
                {"Medicine", "Intelligence"}, {"Occult", "Intelligence"}, {"Science", "Intelligence"},
                {"Survival", "Intelligence"}, {"Tradecraft", "Intelligence"},
                {"Arts", "Wits"}, {"Biz", "Wits"}, {"Meditation", "Wits"}, {"Rapport", "Wits"},
                {"Shadowing", "Wits"}, {"Tactics", "Wits"}, {"Weave", "Wits"},
                {"Disguise", "Appearance"}, {"Intimidation", "Appearance"}, {"Style", "Appearance"},
                {"Diplomacy", "Manipulation"}, {"Hypnosis", "Manipulation"}, {"Interrogation", "Manipulation"},
                {"Seduction", "Manipulation"}, {"Streetwise", "Manipulation"}, {"Subterfuge", "Manipulation"},
                {"Animal Training", "Charisma"}, {"Carousing", "Charisma"}, {"Command", "Charisma"},
                {"Etiquette", "Charisma"}, {"Instruction", "Charisma"}, {"Perform", "Charisma"}
        };
        for (String[] abilityInfo : abilityData) {
            String abilityName = abilityInfo[0];
            String associatedAttribute = abilityInfo[1];

            Ability ability = new Ability();
            ability.setName(abilityName);
            ability.setValue(0);
            // Set initial values for Stamina abilities
            if (abilityName.equals("Endurance") || abilityName.equals("Resistance")) {
                ability.setValue(3);
            }
            ability.setAssociatedAttribute(associatedAttribute);
            ability.setGameChar(this);
            abilities.add(ability);
        }
    }

    private void initializeBackgrounds() {
        String[] backgroundNames = {
                "Allies", "Attunement", "Backing", "Cipher", "Contacts", "Dormancy", "Equipment",
                "Eufiber", "Favors", "Followers", "Influence", "Mentor", "Node", "Rank", "Resources"};
        for (String backgroundName : backgroundNames) {
            Background background = new Background();
            background.setName(backgroundName);
            background.setValue(0);
            background.setGameChar(this);
            backgrounds.add(background);
        }
    }

    private void initializeMegaAttributes() {
        String[] megaAttributeNames = {
                "Mega-Strength", "Mega-Dexterity", "Mega-Stamina",
                "Mega-Perception", "Mega-Intelligence", "Mega-Wits",
                "Mega-Appearance", "Mega-Manipulation", "Mega-Charisma"};
        for (String megaAttributeName : megaAttributeNames) {
            MegaAttribute megaAttribute = new MegaAttribute();
            megaAttribute.setName(megaAttributeName);
            megaAttribute.setValue(0);
            megaAttribute.setGameChar(this);
            megaAttributes.add(megaAttribute);
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

//    Method to retrieve the value of a specific background by name
    public int getBackgroundValue(String backgroundName) {
        for (Background background : backgrounds) {
            if (background.getName().equalsIgnoreCase(backgroundName)) {
                return background.getValue();
            }
        }
//        Return a default value or handle the case when the ability is not found
        return 0;
    }

    @Override
    public String toString() {
        return "GameChar{" +
                "id=" + id +
                ", player='" + player + '\'' +
                ", name='" + name + '\'' +
                ", novaName='" + novaName + '\'' +
                ", concept='" + concept + '\'' +
                ", nature='" + nature + '\'' +
                ", allegiance='" + allegiance + '\'' +
                ", description='" + description + '\'' +
                ", willpowerBonus=" + willpowerBonus +
                ", willpowerNova=" + willpowerNova +
                ", quantumBonus=" + quantumBonus +
                ", quantumNova=" + quantumNova +
                ", quantumPoolBonus=" + quantumPoolBonus +
                ", initiativeBonus=" + initiativeBonus +
                ", taint=" + taint +
                ", attributePoints=" + attributePoints +
                ", abilityPoints=" + abilityPoints +
                ", backgroundPoints=" + backgroundPoints +
                ", bonusPoints=" + bonusPoints +
                ", novaPoints=" + novaPoints +
                ", experiencePoints=" + experiencePoints +
                '}';
    }
}
