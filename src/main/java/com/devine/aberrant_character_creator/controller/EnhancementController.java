package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.Ability;
import com.devine.aberrant_character_creator.model.Enhancement;
import com.devine.aberrant_character_creator.model.MegaAttribute;
import com.devine.aberrant_character_creator.model.Specialty;
import com.devine.aberrant_character_creator.repository.EnhancementRepository;
import com.devine.aberrant_character_creator.repository.MegaAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/enhancements")
public class EnhancementController {

    @Autowired
    private EnhancementRepository enhancementRepository;

    @Autowired
    private MegaAttributeRepository megaAttributeRepository;

    @GetMapping("/megaAttribute/{megaAttributeId}")
    public ResponseEntity<List<Enhancement>> getEnhancementByMegaAttribute(@PathVariable Long megaAttributeId) {
        Optional<MegaAttribute> megaAttribute = megaAttributeRepository.findById(megaAttributeId);
        if (megaAttribute.isPresent()) {
            List<Enhancement> enhancements = megaAttribute.get().getEnhancements();
            return ResponseEntity.ok(enhancements);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/megaAttribute/{megaAttributeId}")
    public ResponseEntity<Enhancement> createEnhancement(@PathVariable Long megaAttributeId, @RequestBody Enhancement enhancementRequest) {
        Optional<MegaAttribute> megaAttribute = megaAttributeRepository.findById(megaAttributeId);
        if (megaAttribute.isPresent()) {
            Enhancement enhancement = new Enhancement();
            enhancement.setName(enhancementRequest.getName());
            enhancement.setValue(0);
            enhancement.setExpValue(0);
            enhancement.setDescription("");
            enhancement.setMegaAttribute(megaAttribute.get());
            enhancementRepository.save(enhancement);
            return ResponseEntity.ok(enhancement);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEnhancementById(@PathVariable Long id) {
        if (!enhancementRepository.existsById(id)) {
            throw new GameCharNotFoundException(id);
        }
        enhancementRepository.deleteById(id);
        return "Enhancement with id " + id + "has been successfully deleted.";
    }

}
