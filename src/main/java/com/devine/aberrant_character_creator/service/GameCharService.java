package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.AbilityUpdateDTO;
import com.devine.aberrant_character_creator.dto.AttributeUpdateDTO;
import com.devine.aberrant_character_creator.dto.BackgroundUpdateDTO;
import com.devine.aberrant_character_creator.dto.GameCharUpdateDTO;
import com.devine.aberrant_character_creator.model.*;

import java.util.List;
import java.util.Optional;

public interface GameCharService {

    GameChar createChar(GameChar gameChar);
    List<GameChar> findAll();
    Optional<GameChar> findById(Long id);
    Boolean existsById(Long id);
    String deleteById(Long id);
    GameChar updateChar(GameCharUpdateDTO updateDTO, Long id);
    GameChar allocateAttributePoints(AttributeUpdateDTO updateDTO, Long id);
    List<Attribute> getCharAttributes(Long id);
    GameChar allocateAbilityPoints(AbilityUpdateDTO updateDTO, Long id);
    List<Ability> getCharAbilities(Long id);
    GameChar allocateBackgroundPoints(BackgroundUpdateDTO updateDTO, Long id);
    List<Background> getCharBackgrounds(Long id);
    Flaw addFlawToGameChar(Long id, Flaw newFlaw);

}
