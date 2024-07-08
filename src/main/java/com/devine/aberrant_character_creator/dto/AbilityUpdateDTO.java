package com.devine.aberrant_character_creator.dto;

import lombok.Data;

import java.util.List;

@Data
public class AbilityUpdateDTO {

    private List<AbilityUpdateDTO.AbilityDTO> abilities;

    @Data
    public static class AbilityDTO {
        private String name;
        private int value;
    }

}
