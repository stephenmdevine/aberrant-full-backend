package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MegaAttribute extends AbstractValue {

    @ManyToOne
    @JoinColumn(name = "game_char_id")
    @JsonBackReference
    private GameChar gameChar;

    @OneToOne
    @JoinColumn(name = "attribute_id")
    @JsonBackReference
    private Attribute attribute;

    @OneToMany(mappedBy = "megaAttribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Enhancement> enhancements = new ArrayList<>();

}
