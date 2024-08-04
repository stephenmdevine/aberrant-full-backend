package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Attribute extends AbstractValue {

    private int bonusValue;
    private int novaValue;
    private int novaPurchased;

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference("attribute-gameChar")
    private GameChar gameChar;

    @OneToOne(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("attribute-quality")
    private Quality quality;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("power-attribute")
    private List<Power> powers = new ArrayList<>();

    @Override
    public String toString() {
        return "Attribute{" +
                "bonusValue=" + bonusValue +
                ", novaValue=" + novaValue +
                ", novaPurchased=" + novaPurchased +
                ", quality=" + quality +
                ", powers=" + powers +
                '}';
    }
}
