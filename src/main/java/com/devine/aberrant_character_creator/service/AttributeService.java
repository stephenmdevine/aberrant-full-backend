package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.AttributeUpdateDTO;
import com.devine.aberrant_character_creator.dto.QualityDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.Quality;
import com.devine.aberrant_character_creator.repository.AttributeRepository;
import com.devine.aberrant_character_creator.repository.GameCharRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private GameCharRepository gameCharRepository;

    public Attribute updateQuality(Long attributeId, QualityDTO qualityDTO) {
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new GameCharNotFoundException(attributeId));

        Quality quality = attribute.getQuality();
        if (quality == null) {
            quality = new Quality();
            quality.setAttribute(attribute);
        }
        quality.setName(qualityDTO.getName());
        attribute.setQuality(quality);

        return attributeRepository.save(attribute);
    }

    public Optional<Attribute> findById(Long attributeId) {
        Optional<Attribute> attribute = attributeRepository.findById(attributeId);
        return attribute;
    }

    public Attribute updateAttribute(AttributeUpdateDTO.AttributeDTO attributeDTO, Long gameId) {
        Attribute attribute = attributeRepository.findByGameCharIdAndName(gameId, attributeDTO.getName())
                .orElseThrow(() -> new GameCharNotFoundException(gameId));

        attribute.setValue(attributeDTO.getValue());
        attribute.setBonusValue(attributeDTO.getBonusValue());
        attribute.setNovaValue(attributeDTO.getNovaValue());
        attribute.setExpValue(attributeDTO.getExpValue());

        if (attribute.getValue() + attribute.getBonusValue() > 4) {
            if (attribute.getQuality() == null) {
                attribute.setQuality(new Quality());
            }
            attribute.getQuality().setName(attributeDTO.getQualityName());
        } else {
            attribute.setQuality(null); // Remove Quality if total value <= 4
        }

        return attributeRepository.save(attribute);
    }

}
