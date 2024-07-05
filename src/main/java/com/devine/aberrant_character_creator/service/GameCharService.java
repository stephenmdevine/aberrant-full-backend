package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.AttributeUpdateDTO;
import com.devine.aberrant_character_creator.dto.GameCharUpdateDTO;
import com.devine.aberrant_character_creator.model.GameChar;

import java.util.List;
import java.util.Optional;

public interface GameCharService {

    GameChar createChar(GameChar gameChar);
    List<GameChar> findAll();
    Optional<GameChar> findById(Long id);
    Boolean existsById(Long id);
    String deleteById(Long id);
    GameChar updateChar(GameCharUpdateDTO updateDTO, Long id);
    GameChar allocateAttributePoints(AttributeUpdateDTO updateDTO, Long id, int maxTotalValue);

}
