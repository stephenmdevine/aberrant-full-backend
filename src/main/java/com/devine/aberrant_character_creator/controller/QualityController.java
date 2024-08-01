package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.dto.QualityDTO;
import com.devine.aberrant_character_creator.model.Ability;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.Quality;
import com.devine.aberrant_character_creator.model.Specialty;
import com.devine.aberrant_character_creator.repository.AttributeRepository;
import com.devine.aberrant_character_creator.repository.QualityRepository;
import com.devine.aberrant_character_creator.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/attribute")
public class QualityController {

    @Autowired
    QualityRepository qualityRepository;
    @Autowired
    AttributeService attributeService;

    @GetMapping("/quality/{attributeId}")
    public ResponseEntity<Quality> getQualityByAttribute(@PathVariable Long attributeId) {
        Optional<Attribute> attribute = attributeService.findById(attributeId);
        if (attribute.isPresent()) {
            Quality qualities = attribute.get().getQuality();
            return ResponseEntity.ok(qualities);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{attributeId}/newQuality")
    public ResponseEntity<Quality> createQuality(@PathVariable Long attributeId, @RequestBody Quality qualityRequest) {
        Optional<Attribute> attribute = attributeService.findById(attributeId);
        if (attribute.isPresent()) {
            Quality quality = new Quality();
            quality.setName(qualityRequest.getName());
            quality.setAttribute(attribute.get());
            qualityRepository.save(quality);
            return ResponseEntity.ok(quality);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{attributeId}/quality")
    public ResponseEntity<Attribute> updateAttributeQuality(@PathVariable Long attributeId, @RequestBody QualityDTO qualityDTO){
        Attribute updatedAttribute = attributeService.updateQuality(attributeId, qualityDTO);
        return ResponseEntity.ok(updatedAttribute);
    }

}
