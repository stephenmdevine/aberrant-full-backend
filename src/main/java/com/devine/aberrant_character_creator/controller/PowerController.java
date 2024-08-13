package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.dto.PowerDTO;
import com.devine.aberrant_character_creator.model.Power;
import com.devine.aberrant_character_creator.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/powers")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @PostMapping("/{id}")
    public ResponseEntity<Power> createPower(@RequestBody PowerDTO powerDTO, @PathVariable Long id) {
        Power newPower = powerService.createPower(powerDTO, id);
        System.out.println("Post mapping initiated...");
        return ResponseEntity.ok(newPower);
    }



}
