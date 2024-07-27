package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.repository.FlawRepository;
import com.devine.aberrant_character_creator.repository.MeritRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/flawsAndMerits")
public class FlawOrMeritController {

    @Autowired
    FlawRepository flawRepository;
    @Autowired
    MeritRepository meritRepository;

    @DeleteMapping("/{id}/flaw")
    public String deleteFlawById(@PathVariable Long id) {
        if (!flawRepository.existsById(id)) {
            throw new GameCharNotFoundException(id);
        }
        flawRepository.deleteById(id);
        return "Flaw with id " + id + "has been successfully deleted.";
    }

    @DeleteMapping("/{id}/merit")
    public String deleteMeritById(@PathVariable Long id) {
        if (!meritRepository.existsById(id)) {
            throw new GameCharNotFoundException(id);
        }
        meritRepository.deleteById(id);
        return "Merit with id " + id + "has been successfully deleted.";
    }

}
