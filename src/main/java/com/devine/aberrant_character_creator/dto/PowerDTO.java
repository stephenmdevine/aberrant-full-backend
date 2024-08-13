package com.devine.aberrant_character_creator.dto;

import lombok.Data;

@Data
public class PowerDTO {

    private String name;
    private int value;
    private int level;
    private int quantumMinimum;
    private boolean hasExtra;
    private String extraName;

}
