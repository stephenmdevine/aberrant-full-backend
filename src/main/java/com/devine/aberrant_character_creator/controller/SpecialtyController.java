package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.Ability;
import com.devine.aberrant_character_creator.model.Specialty;
import com.devine.aberrant_character_creator.repository.AbilityRepository;
import com.devine.aberrant_character_creator.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private AbilityRepository abilityRepository;

    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<List<Specialty>> getSpecialtyByAbility(@PathVariable Long abilityId) {
        Optional<Ability> ability = abilityRepository.findById(abilityId);
        if (ability.isPresent()) {
            List<Specialty> specialties = ability.get().getSpecialties();
            return ResponseEntity.ok(specialties);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ability/{abilityId}")
    public ResponseEntity<Specialty> createSpecialty(@PathVariable Long abilityId, @RequestBody Specialty specialtyRequest) {
        Optional<Ability> ability = abilityRepository.findById(abilityId);
        if (ability.isPresent()) {
            Specialty specialty = new Specialty();
            specialty.setName(specialtyRequest.getName());
            specialty.setValue(0);
            specialty.setExpValue(0);
            specialty.setAbility(ability.get());
            specialtyRepository.save(specialty);
            return ResponseEntity.ok(specialty);
        }   else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        if (!specialtyRepository.existsById(id)) {
            throw new GameCharNotFoundException(id);
        }
        specialtyRepository.deleteById(id);
        return "Specialty with id " + id + "has been successfully deleted.";
    }

}
