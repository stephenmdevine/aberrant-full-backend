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

    private int novaPurchased;

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference
    private GameChar gameChar;

    @OneToOne(mappedBy = "attribute")
    @JsonManagedReference
    private MegaAttribute megaAttribute;

    @OneToOne(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Quality quality;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Power> powers = new ArrayList<>();

}
