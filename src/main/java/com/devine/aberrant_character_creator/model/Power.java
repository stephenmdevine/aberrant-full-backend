package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Power extends AbstractValue {

    private int level;
    private int quantumMinimum;
    private String description;
    private Boolean hasExtra;
    private String extraName;

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference
    private GameChar gameChar;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    @JsonBackReference
    private Attribute attribute;

}
