package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ability extends AbstractValue {

    private int novaPurchased;
    private String associatedAttribute;

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference("ability-gameChar")
    private GameChar gameChar;

    @OneToMany(mappedBy = "ability", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("specialty-ability")
    private List<Specialty> specialties = new ArrayList<>();

}
