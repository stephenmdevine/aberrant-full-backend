package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Merit extends AbstractValue {

    private String description;

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference("merit-gameChar")
    private GameChar gameChar;

}
