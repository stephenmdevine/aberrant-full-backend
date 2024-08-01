package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.dto.QualityDTO;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.Quality;
import com.devine.aberrant_character_creator.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/{attributeId}/quality")
    public ResponseEntity<Quality> fetchQualityByAttribute(@PathVariable Long attributeId) {
        Quality quality = attributeService.findQualityByAttributeId(attributeId);
        if (quality != null) {
            return ResponseEntity.ok(quality);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to create or update a quality for a given attribute
    @PostMapping("/{attributeId}/quality")
    public ResponseEntity<Attribute> addOrUpdateQuality(@PathVariable Long attributeId, @RequestBody QualityDTO qualityDTO) {
        Attribute updatedAttribute = attributeService.addOrUpdateQuality(attributeId, qualityDTO);
        return ResponseEntity.ok(updatedAttribute);
    }
}
