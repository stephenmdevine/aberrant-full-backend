package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.QualityDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.Quality;
import com.devine.aberrant_character_creator.repository.AttributeRepository;
import com.devine.aberrant_character_creator.repository.QualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private QualityRepository qualityRepository;

    public Attribute addOrUpdateQuality(Long attributeId, QualityDTO qualityDTO) {
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

    public Quality findQualityByAttributeId(Long attributeId) {
        return qualityRepository.findByAttributeId(attributeId);
    }
}
