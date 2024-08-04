package com.devine.aberrant_character_creator.dto;

import com.devine.aberrant_character_creator.model.Enhancement;
import lombok.Data;

import java.util.List;

@Data
public class MegaAttributeUpdateDTO {

    private List<MegaAttributeUpdateDTO.MegaAttributeDTO> megaAttributes;

    @Data
    public static class MegaAttributeDTO {
        private String name;
        private int value;
        private int expValue;
        private List<Enhancement> enhancements;
    }

}
