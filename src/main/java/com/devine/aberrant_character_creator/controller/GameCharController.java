package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.dto.GameCharUpdateDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.GameChar;
import com.devine.aberrant_character_creator.service.GameCharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class GameCharController {

    @Autowired
    private GameCharService gameCharService;

    public GameCharController(GameCharService gameCharService) {
        this.gameCharService = gameCharService;
    }

    @PostMapping("/character")
    GameChar newChar(@RequestBody GameChar newChar) {
        return (GameChar) gameCharService.createChar(newChar);
    }

    @GetMapping("/characters")
    List<GameChar> getAllChars() {
        return gameCharService.findAll();
    }

    @GetMapping("/character/{id}")
    GameChar getCharById(@PathVariable Long id) {
        return gameCharService.findById(id)
                .orElseThrow(() -> new GameCharNotFoundException(id));
    }

    @PutMapping("/character/{id}")
    ResponseEntity<GameChar> updateChar(@RequestBody GameCharUpdateDTO updateDTO, @PathVariable Long id) {
        GameChar updatedGameChar = gameCharService.updateChar(updateDTO, id);
        return ResponseEntity.ok(updatedGameChar);
    }

    @DeleteMapping("/character/{id}")
    String deleteChar(@PathVariable Long id) {
        if (!gameCharService.existsById(id)) {
            throw new GameCharNotFoundException(id);
        }
        gameCharService.deleteById(id);
        return "Character with id " + id + "has been successfully deleted.";
    }

}
