package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Specialty extends AbstractValue {

    @ManyToOne
    @JoinColumn(name = "ability_id")
    @JsonBackReference
    private Ability ability;

}
