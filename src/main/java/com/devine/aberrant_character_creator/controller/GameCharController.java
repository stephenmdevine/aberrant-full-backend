package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.dto.*;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.*;
import com.devine.aberrant_character_creator.service.GameCharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class GameCharController {

    @Autowired
    private GameCharService gameCharService;

    public GameCharController(GameCharService gameCharService) {
        this.gameCharService = gameCharService;
    }

//    Endpoint to save new character data
    @PostMapping("/character")
    public ResponseEntity<GameChar> newChar(@RequestBody GameChar newChar) {
        try {
            GameChar createdChar = gameCharService.createChar(newChar);
            return ResponseEntity.ok(createdChar);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    Endpoint to retrieve all characters
    @GetMapping("/characters")
    List<GameChar> getAllChars() {
        return gameCharService.findAll();
    }

//    Endpoint to retrieve a character by its ID
    @GetMapping("/character/{id}")
    GameChar getCharById(@PathVariable Long id) {
        return gameCharService.findById(id)
                .orElseThrow(() -> new GameCharNotFoundException(id));
    }

//    Endpoint to update a character's data by its ID
    @PutMapping("/character/{id}")
    ResponseEntity<GameChar> updateChar(@RequestBody GameCharUpdateDTO updateDTO, @PathVariable Long id) {
        GameChar updatedGameChar = gameCharService.updateChar(updateDTO, id);
        return ResponseEntity.ok(updatedGameChar);
    }

    @PutMapping("/character/additionalStats/{id}")
    ResponseEntity<GameChar> updateAdditionalStats(@RequestBody GameCharUpdateDTO updateDTO, @PathVariable Long id) {
        GameChar updatedGameChar = gameCharService.updateAdditionalCharStats(updateDTO, id);
        return ResponseEntity.ok(updatedGameChar);
    }

//    Endpoint to delete a character by its ID
    @DeleteMapping("/character/{id}")
    String deleteChar(@PathVariable Long id) {
        if (!gameCharService.existsById(id)) {
            throw new GameCharNotFoundException(id);
        }
        gameCharService.deleteById(id);
        return "Character with id " + id + "has been successfully deleted.";
    }

//    Endpoint to retrieve a character's attributes by its ID
    @GetMapping("/attributes/{id}")
    public ResponseEntity<List<Attribute>> getCharAttributes(@PathVariable Long id) {
        List<Attribute> attributes = gameCharService.getCharAttributes(id);
        return ResponseEntity.ok(attributes);
    }

    @PutMapping("/allocateAttributePoints/{id}")
    GameChar allocateAttributePoints(@RequestBody AttributeUpdateDTO updateDTO, @PathVariable Long id) {
        return gameCharService.allocateAttributePoints(updateDTO, id);
    }

//    Endpoint to retrieve a character's abilities by its ID
    @GetMapping("/abilities/{id}")
    public ResponseEntity<List<Ability>> getCharAbilities(@PathVariable Long id) {
        List<Ability> abilities = gameCharService.getCharAbilities(id);
        return ResponseEntity.ok(abilities);
    }

    @PutMapping("/allocateAbilityPoints/{id}")
    GameChar allocateAbilityPoints(@RequestBody AbilityUpdateDTO updateDTO, @PathVariable Long id) {
        return gameCharService.allocateAbilityPoints(updateDTO, id);
    }

    //    Endpoint to retrieve a character's backgrounds by its ID
    @GetMapping("/backgrounds/{id}")
    public ResponseEntity<List<Background>> getCharBackgrounds(@PathVariable Long id) {
        List<Background> backgrounds = gameCharService.getCharBackgrounds(id);
        return ResponseEntity.ok(backgrounds);
    }

    @PutMapping("/allocateBackgroundPoints/{id}")
    GameChar allocateBackgroundPoints(@RequestBody BackgroundUpdateDTO updateDTO, @PathVariable Long id) {
        return gameCharService.allocateBackgroundPoints(updateDTO, id);
    }

    @PostMapping("/{id}/flaws")
    public ResponseEntity<Flaw> addFlaw(@PathVariable Long id, @RequestBody Flaw flaw) {
        Flaw createdFlaw = gameCharService.addFlawToGameChar(id, flaw);
        return ResponseEntity.ok(createdFlaw);
    }

    @PostMapping("/{id}/merits")
    public ResponseEntity<Merit> addMerit(@PathVariable Long id, @RequestBody Merit merit) {
        Merit createdMerit = gameCharService.addMeritToGameChar(id, merit);
        return ResponseEntity.ok(createdMerit);
    }

    @PutMapping("/allocateMegaAttributePoints/{id}")
    GameChar allocateMegaAttributePoints(@RequestBody MegaAttributeUpdateDTO updateDTO, @PathVariable Long id) {
        return gameCharService.allocateMegaAttributePoints(updateDTO, id);
    }

}
