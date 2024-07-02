package com.devine.aberrant_character_creator.controller;

import com.devine.aberrant_character_creator.repository.GameCharRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class GameCharController {

    @Autowired
    private GameCharRepository gameCharRepository;
}
