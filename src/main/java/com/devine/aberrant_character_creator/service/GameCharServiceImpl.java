package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.AttributeUpdateDTO;
import com.devine.aberrant_character_creator.dto.GameCharUpdateDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.GameChar;
import com.devine.aberrant_character_creator.repository.GameCharRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameCharServiceImpl implements GameCharService {

    private GameCharRepository gameCharRepository;

    public GameCharServiceImpl(GameCharRepository gameCharRepository) {
        this.gameCharRepository = gameCharRepository;
    }

    @Override
    public GameChar createChar(GameChar newChar) {
        GameChar gameChar = new GameChar();
        gameChar.setPlayer(newChar.getPlayer());
        gameChar.setName(newChar.getName());
        gameChar.setNovaName(newChar.getNovaName());

        gameChar.setConcept(newChar.getConcept());
        gameChar.setNature(newChar.getNature());
        gameChar.setAllegiance(newChar.getAllegiance());
        gameChar.setDescription(newChar.getDescription());

        gameChar.setAttributePoints(newChar.getAttributePoints());
        gameChar.setAbilityPoints(newChar.getAbilityPoints());
        gameChar.setBackgroundPoints(newChar.getBackgroundPoints());
        gameChar.setBonusPoints(newChar.getBonusPoints());
        gameChar.setNovaPoints(newChar.getNovaPoints());
        gameChar.setExperiencePoints(newChar.getExperiencePoints());

        return (GameChar) gameCharRepository.save(gameChar);
    }

    @Override
    public List<GameChar> findAll() {
        return gameCharRepository.findAll();
    }

    @Override
    public Optional<GameChar> findById(Long id) {
        return gameCharRepository.findById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return gameCharRepository.existsById(id);
    }

    @Override
    public String deleteById(Long id) {
        gameCharRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public GameChar updateChar(GameCharUpdateDTO updateDTO, Long id) {
        return gameCharRepository.findById(id)
                .map(gameChar -> {
                    if (updateDTO.getPlayer() != null) {gameChar.setPlayer(updateDTO.getPlayer());}
                    if (updateDTO.getName() != null) {gameChar.setName(updateDTO.getName());}
                    if (updateDTO.getNovaName() != null) {gameChar.setNovaName(updateDTO.getNovaName());}

                    if (updateDTO.getConcept() != null) {gameChar.setConcept(updateDTO.getConcept());}
                    if (updateDTO.getNature() != null) {gameChar.setNature(updateDTO.getNature());}
                    if (updateDTO.getAllegiance() != null) {gameChar.setAllegiance(updateDTO.getAllegiance());}
                    if (updateDTO.getDescription() != null) {gameChar.setDescription(updateDTO.getDescription());}


                    if (updateDTO.getAttributePoints() != 0) {gameChar.setAttributePoints(updateDTO.getAttributePoints());}
                    if (updateDTO.getAbilityPoints() != 0) {gameChar.setAbilityPoints(updateDTO.getAbilityPoints());}
                    if (updateDTO.getBackgroundPoints() != 0) {gameChar.setBackgroundPoints(updateDTO.getBackgroundPoints());}
                    if (updateDTO.getBonusPoints() != 0) {gameChar.setBonusPoints(updateDTO.getBonusPoints());}
                    if (updateDTO.getNovaPoints() != 0) {gameChar.setNovaPoints(updateDTO.getNovaPoints());}
                    if (updateDTO.getExperiencePoints() != 0) {gameChar.setExperiencePoints(updateDTO.getExperiencePoints());}

                    return gameCharRepository.save(gameChar);
                })
                .orElseThrow(() -> new GameCharNotFoundException(id));
    }

    @Override
    public GameChar allocateAttributePoints(AttributeUpdateDTO updateDTO, Long id, int maxTotalValue) {

        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }

        GameChar gameChar = optionalGameChar.get();
        int totalValue = updateDTO.getAttributes().stream().mapToInt(AttributeUpdateDTO.AttributeDTO::getValue).sum();

        if (totalValue > maxTotalValue) {
            throw new IllegalArgumentException("Total value of attributes exceeds the allowed maximum.");
        }

        for (AttributeUpdateDTO.AttributeDTO attributeDTO : updateDTO.getAttributes()) {
            gameChar.getAttributes().stream().filter(attribute -> attribute.getName().equals(attributeDTO.getName())).findFirst().ifPresent(attribute -> attribute.setValue(attributeDTO.getValue()));
        }

        return gameCharRepository.save(gameChar);
    }

}
