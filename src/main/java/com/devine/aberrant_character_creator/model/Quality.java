package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Quality extends AbstractValue {

    private String description;

    @OneToOne
    @JoinColumn(name = "attribute_id")
    @JsonBackReference
    private Attribute attribute;

}
