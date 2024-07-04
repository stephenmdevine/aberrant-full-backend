package com.devine.aberrant_character_creator.dto;

import com.devine.aberrant_character_creator.model.AttributeSet;
import lombok.Data;

import java.util.Map;

@Data
public class AllocateAttrPtsDTO {

    private AttributeSet attributeSet;
    private Map<String, Integer> attributeValues;
    private Map<String, String> qualityDetails;

}
