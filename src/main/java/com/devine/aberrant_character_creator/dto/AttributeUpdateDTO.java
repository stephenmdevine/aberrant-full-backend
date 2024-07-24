package com.devine.aberrant_character_creator.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttributeUpdateDTO {

    private List<AttributeDTO> attributes;

    @Data
    public static class AttributeDTO {
        private String name;
        private int value;
        private int bonusValue;
        private int novaValue;
        private int expValue;
    }

}
