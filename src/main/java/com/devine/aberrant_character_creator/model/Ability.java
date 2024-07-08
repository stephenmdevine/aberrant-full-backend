package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ability extends AbstractValue {

    private int novaPurchased;

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference
    private GameChar gameChar;

//    @OneToMany(mappedBy = "ability", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Specialty> specialties;

}
