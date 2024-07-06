package com.devine.aberrant_character_creator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Attribute extends AbstractValue {

    private int novaPurchased;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_char_id")
    private GameChar gameChar;

//    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Quality> qualities;
//    @OneToMany(mappedBy = "attribute")
//    private List<Power> powers;

}
