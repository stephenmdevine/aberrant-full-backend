package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.dto.EnhancementDTO;
import com.devine.aberrant_character_creator.dto.QualityDTO;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.Enhancement;
import com.devine.aberrant_character_creator.model.MegaAttribute;
import com.devine.aberrant_character_creator.model.Quality;
import com.devine.aberrant_character_creator.service.MegaAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/megaAttributes")
public class MegaAttributeController {

    @Autowired
    private MegaAttributeService megaAttributeService;

    @GetMapping("/{megaAttributeId}/enhancements")
    public ResponseEntity<List<Enhancement>> getEnhancements(@PathVariable Long megaAttributeId) {
        List<Enhancement> enhancements = megaAttributeService.getEnhancements(megaAttributeId);
        return ResponseEntity.ok(enhancements);
    }

    // Endpoint to create an enhancement for a given mega-attribute
    @PostMapping("/{megaAttributeId}/enhancements")
    public ResponseEntity<MegaAttribute> addEnhancement(@PathVariable Long megaAttributeId, @RequestBody EnhancementDTO enhancementDTO) {
        MegaAttribute updatedMegaAttribute = megaAttributeService.addEnhancement(megaAttributeId, enhancementDTO);
        return ResponseEntity.ok(updatedMegaAttribute);
    }

    @DeleteMapping("/{megaAttributeId}/enhancements/{enhancementId}")
    public ResponseEntity<Void> deleteEnhancement(
            @PathVariable Long megaAttributeId,
            @PathVariable Long enhancementId) {
        megaAttributeService.deleteEnhancement(megaAttributeId, enhancementId);
        return ResponseEntity.noContent().build();
    }

}
