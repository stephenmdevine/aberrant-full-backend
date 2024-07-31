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
                    // Update basic information
                    if (updateDTO.getPlayer() != null) {gameChar.setPlayer(updateDTO.getPlayer());}
                    if (updateDTO.getName() != null) {gameChar.setName(updateDTO.getName());}
                    if (updateDTO.getNovaName() != null) {gameChar.setNovaName(updateDTO.getNovaName());}

                    if (updateDTO.getConcept() != null) {gameChar.setConcept(updateDTO.getConcept());}
                    if (updateDTO.getNature() != null) {gameChar.setNature(updateDTO.getNature());}
                    if (updateDTO.getAllegiance() != null) {gameChar.setAllegiance(updateDTO.getAllegiance());}
                    if (updateDTO.getDescription() != null) {gameChar.setDescription(updateDTO.getDescription());}

                    // Update points
                    gameChar.setAttributePoints(updateDTO.getAttributePoints());
                    gameChar.setAbilityPoints(updateDTO.getAbilityPoints());
                    gameChar.setBackgroundPoints(updateDTO.getBackgroundPoints());
                    gameChar.setBonusPoints(updateDTO.getBonusPoints());
                    gameChar.setNovaPoints(updateDTO.getNovaPoints());
                    gameChar.setExperiencePoints(updateDTO.getExperiencePoints());

                    // Update additional stats
                    gameChar.setWillpowerBonus(updateDTO.getWillpowerBonus());
                    gameChar.setQuantumBonus(updateDTO.getQuantumBonus());
                    gameChar.setQuantumNova(updateDTO.getQuantumNova());
                    gameChar.setQuantumPoolBonus(updateDTO.getQuantumPoolBonus());
                    gameChar.setInitiativeBonus(updateDTO.getInitiativeBonus());
                    gameChar.setTaint(updateDTO.getTaint());

                    // Update attributes
                    if (updateDTO.getAttributes() != null) {
                        for (AttributeUpdateDTO.AttributeDTO attrDTO : updateDTO.getAttributes()) {
                            Attribute attribute = gameChar.getAttributes()
                                    .stream()
                                    .filter(a -> a.getName().equals(attrDTO.getName()))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException());
                            attribute.setValue(attrDTO.getValue());
                        }
                    }

                    // Update abilities
                    if (updateDTO.getAbilities() != null) {
                        for (AbilityUpdateDTO.AbilityDTO abilDTO : updateDTO.getAbilities()) {
                            Ability ability = gameChar.getAbilities()
                                    .stream()
                                    .filter(a -> a.getName().equals(abilDTO.getName()))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException());
                            ability.setValue(abilDTO.getValue());
                        }
                    }

                    // Update backgrounds
                    if (updateDTO.getBackgrounds() != null) {
                        for (BackgroundUpdateDTO.BackgroundDTO bkgDTO : updateDTO.getBackgrounds()) {
                            Background background = gameChar.getBackgrounds()
                                    .stream()
                                    .filter(b -> b.getName().equals(bkgDTO.getName()))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException());
                            background.setValue(bkgDTO.getValue());
                        }
                    }

                    // Update flaws
                    if (updateDTO.getFlaws() != null) {
                        gameChar.getFlaws().clear();
                        gameChar.getFlaws().addAll(updateDTO.getFlaws());
                    }

                    // Update merits
                    if (updateDTO.getMerits() != null) {
                        gameChar.getMerits().clear();
                        gameChar.getMerits().addAll(updateDTO.getMerits());
                    }

                    return gameCharRepository.save(gameChar);
                })
                .orElseThrow(() -> new GameCharNotFoundException(id));
    }

    @Override
    public GameChar updateAdditionalCharStats(GameCharUpdateDTO updateDTO, Long id) {
        return gameCharRepository.findById(id)
                .map(gameChar -> {
                    // Update points
                    gameChar.setBonusPoints(updateDTO.getBonusPoints());
                    gameChar.setNovaPoints(updateDTO.getNovaPoints());
                    gameChar.setExperiencePoints(updateDTO.getExperiencePoints());

                    // Update additional stats
                    gameChar.setWillpowerBonus(updateDTO.getWillpowerBonus());
                    gameChar.setQuantumBonus(updateDTO.getQuantumBonus());
                    gameChar.setQuantumNova(updateDTO.getQuantumNova());
                    gameChar.setQuantumPoolBonus(updateDTO.getQuantumPoolBonus());
                    gameChar.setInitiativeBonus(updateDTO.getInitiativeBonus());
                    gameChar.setTaint(updateDTO.getTaint());

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
                        attribute.setValue(attributeDTO.getValue()); // Set whatever value is provided
                        attribute.setBonusValue(attributeDTO.getBonusValue()); // Set whatever value is provided
                        attribute.setNovaValue(attributeDTO.getNovaValue()); // Set whatever value is provided
                        attribute.setExpValue(attributeDTO.getExpValue()); // Set whatever value is provided
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
        return flaw;
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
        return merit;
    }

}
