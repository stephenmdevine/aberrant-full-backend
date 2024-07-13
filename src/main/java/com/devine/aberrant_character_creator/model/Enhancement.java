package com.devine.aberrant_character_creator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enhancement extends AbstractValue {

    private String description;

    @ManyToOne
    @JoinColumn(name = "mega_attribute_id")
    @JsonBackReference("enhancement-megaAttribute")
    private MegaAttribute megaAttribute;

}
