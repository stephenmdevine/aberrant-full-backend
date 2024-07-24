package com.devine.aberrant_character_creator.dto;

import lombok.Data;

import java.util.List;

@Data
public class BackgroundUpdateDTO {

    private List<BackgroundDTO> backgrounds;

    @Data
    public static class BackgroundDTO {
        private String name;
        private int value;
        private int bonusValue;
        private int novaValue;
        private int expValue;
    }

}
