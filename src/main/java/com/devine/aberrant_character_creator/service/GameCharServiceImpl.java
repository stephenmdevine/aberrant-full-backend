package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.AbilityUpdateDTO;
import com.devine.aberrant_character_creator.dto.AttributeUpdateDTO;
import com.devine.aberrant_character_creator.dto.BackgroundUpdateDTO;
import com.devine.aberrant_character_creator.dto.GameCharUpdateDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.*;
import com.devine.aberrant_character_creator.repository.FlawRepository;
import com.devine.aberrant_character_creator.repository.GameCharRepository;
import com.devine.aberrant_character_creator.repository.MeritRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameCharServiceImpl implements GameCharService {

    @Autowired
    private GameCharRepository gameCharRepository;
    @Autowired
    private FlawRepository flawRepository;
    @Autowired
    private MeritRepository meritRepository;

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
    public GameChar allocateAttributePoints(AttributeUpdateDTO updateDTO, Long id) {

        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }

        GameChar gameChar = optionalGameChar.get();
        if (gameChar.getAttributes() == null) {
            gameChar.setAttributes(new ArrayList<>());
        }

        for (AttributeUpdateDTO.AttributeDTO attributeDTO : updateDTO.getAttributes()) {
            gameChar.getAttributes().stream()
                    .filter(attribute ->
                            attribute.getName().equals(attributeDTO.getName()))
                    .findFirst().ifPresent(attribute -> {
                        attribute.setValue(attributeDTO.getValue());
                        attribute.setBonusValue(attributeDTO.getBonusValue());
                        attribute.setNovaValue(attributeDTO.getNovaValue());
                        attribute.setExpValue(attributeDTO.getExpValue());
                        System.out.println("Updated attribute: " + attribute.getName() + " to values: " +
                                "Value = " + attribute.getValue() +
                                ", Bonus Value = " + attribute.getBonusValue() +
                                ", Nova Value = " + attribute.getNovaValue() +
                                ", Exp Value = " + attribute.getExpValue());
                    });
        }

        return gameCharRepository.save(gameChar);
    }

    @Override
    public List<Attribute> getCharAttributes(Long id) {
        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }
        return optionalGameChar.get().getAttributes();
    }

    @Override
    public GameChar allocateAbilityPoints(AbilityUpdateDTO updateDTO, Long id) {

        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }

        GameChar gameChar = optionalGameChar.get();
        if (gameChar.getAbilities() == null) {
            gameChar.setAbilities(new ArrayList<>());
        }

        for (AbilityUpdateDTO.AbilityDTO abilityDTO : updateDTO.getAbilities()) {
            gameChar.getAbilities().stream()
                    .filter(ability ->
                            ability.getName().equals(abilityDTO.getName()))
                    .findFirst().ifPresent(ability -> {
                        ability.setValue(abilityDTO.getValue());
                        ability.setBonusValue(abilityDTO.getBonusValue());
                        ability.setNovaValue(abilityDTO.getNovaValue());
                        ability.setExpValue(abilityDTO.getExpValue());
                        System.out.println("Updated ability: " + ability.getName() + " to values: " +
                                "Value = " + ability.getValue() +
                                ", Bonus Value = " + ability.getBonusValue() +
                                ", Nova Value = " + ability.getNovaValue() +
                                ", Exp Value = " + ability.getExpValue());
                    });
        }

        return gameCharRepository.save(gameChar);
    }

    @Override
    public List<Ability> getCharAbilities(Long id) {
        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }
        return optionalGameChar.get().getAbilities();
    }

    @Override
    public GameChar allocateBackgroundPoints(BackgroundUpdateDTO updateDTO, Long id) {

        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }

        GameChar gameChar = optionalGameChar.get();
        if (gameChar.getBackgrounds() == null) {
            gameChar.setBackgrounds(new ArrayList<>());
        }

        for (BackgroundUpdateDTO.BackgroundDTO backgroundDTO : updateDTO.getBackgrounds()) {
            gameChar.getBackgrounds().stream()
                    .filter(background ->
                            background.getName().equals(backgroundDTO.getName()))
                    .findFirst().ifPresent(background -> {
                        background.setValue(backgroundDTO.getValue());
                        background.setBonusValue(backgroundDTO.getBonusValue());
                        background.setNovaValue(backgroundDTO.getNovaValue());
                        background.setExpValue(background.getExpValue());
                        System.out.println("Updated background: " + background.getName() + " to values: " +
                                "Value = " + background.getValue() +
                                ", Bonus Value = " + background.getBonusValue() +
                                ", Nova Value = " + background.getNovaValue() +
                                ", Exp Value = " + background.getExpValue());
                    });
        }

        return gameCharRepository.save(gameChar);
    }

    @Override
    public List<Background> getCharBackgrounds(Long id) {
        Optional<GameChar> optionalGameChar = gameCharRepository.findById(id);
        if (!optionalGameChar.isPresent()) {
            throw new GameCharNotFoundException(id);
        }
        return optionalGameChar.get().getBackgrounds();
    }

    @Override
    public Flaw addFlawToGameChar(Long id, Flaw newFlaw) {
        GameChar gameChar = gameCharRepository.findById(id)
                .orElseThrow(() -> new GameCharNotFoundException(id));

        Flaw flaw = new Flaw();
        flaw.setName(newFlaw.getName());
        flaw.setValue(newFlaw.getValue());
        flaw.setGameChar(gameChar);
        gameChar.getFlaws().add(flaw); // Ensure the flaw is added to the GameChar's list of flaws
        gameCharRepository.save(gameChar); // Save the updated GameChar entity
        return flawRepository.save(flaw);
    }

    @Override
    public Merit addMeritToGameChar(Long id, Merit newMerit) {
        GameChar gameChar = gameCharRepository.findById(id)
                .orElseThrow(() -> new GameCharNotFoundException(id));

        Merit merit = new Merit();
        merit.setName(newMerit.getName());
        merit.setValue(newMerit.getValue());
        merit.setGameChar(gameChar);
        gameChar.getMerits().add(merit); // Ensure the merit is added to the GameChar's list of merits
        gameCharRepository.save(gameChar); // Save the updated GameChar entity
        return meritRepository.save(merit);
    }

}
