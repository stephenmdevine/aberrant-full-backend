package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.PowerDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.GameChar;
import com.devine.aberrant_character_creator.model.Power;
import com.devine.aberrant_character_creator.repository.AttributeRepository;
import com.devine.aberrant_character_creator.repository.GameCharRepository;
import com.devine.aberrant_character_creator.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerService {

    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private GameCharRepository gameCharRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    public Power createPower(PowerDTO powerDTO, Long id) {
        GameChar gameChar = gameCharRepository.findById(id)
                .orElseThrow(() -> new GameCharNotFoundException(id));

        Power power = new Power();
        power.setName(powerDTO.getName());
        power.setValue(powerDTO.getValue());
        power.setLevel(powerDTO.getLevel());
        power.setQuantumMinimum(powerDTO.getQuantumMinimum());
        power.setHasExtra(powerDTO.isHasExtra());
        power.setExtraName(powerDTO.getExtraName());
        power.setGameChar(gameChar);

        if (powerDTO.getAttributeId() != null) {
            Attribute attribute = attributeRepository.findById(powerDTO.getAttributeId())
                    .orElseThrow(() -> new GameCharNotFoundException(powerDTO.getAttributeId()));
            power.setAttribute(attribute);
        } else {
            power.setAttribute(null);
        }
        System.out.println("New power: " + power.getName() + " to values: " +
                "Value = " + power.getValue() +
                ", Exp Value = " + power.getExpValue() +
                ", Level = " + power.getLevel()
        );

        return powerRepository.save(power);
    }

}
