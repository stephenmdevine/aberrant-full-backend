package com.devine.aberrant_character_creator.exception;

public class GameCharNotFoundException extends RuntimeException {
    public GameCharNotFoundException(Long id) {
        super("Could not find the character with id: " + id);
    }
}
