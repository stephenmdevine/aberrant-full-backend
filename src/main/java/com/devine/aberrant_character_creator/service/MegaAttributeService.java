package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.EnhancementDTO;
import com.devine.aberrant_character_creator.dto.QualityDTO;
import com.devine.aberrant_character_creator.exception.GameCharNotFoundException;
import com.devine.aberrant_character_creator.model.Attribute;
import com.devine.aberrant_character_creator.model.Enhancement;
import com.devine.aberrant_character_creator.model.MegaAttribute;
import com.devine.aberrant_character_creator.model.Quality;
import com.devine.aberrant_character_creator.repository.EnhancementRepository;
import com.devine.aberrant_character_creator.repository.MegaAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MegaAttributeService {

    @Autowired
    private MegaAttributeRepository megaAttributeRepository;
    @Autowired
    private EnhancementRepository enhancementRepository;

    public MegaAttribute addEnhancement(Long megaAttributeId, EnhancementDTO enhancementDTO) {
        MegaAttribute megaAttribute = megaAttributeRepository.findById(megaAttributeId)
                .orElseThrow(() -> new GameCharNotFoundException(megaAttributeId));

        Enhancement enhancement = new Enhancement();
        enhancement.setName(enhancementDTO.getName());
        enhancement.setMegaAttribute(megaAttribute);

        megaAttribute.getEnhancements().add(enhancement);
        enhancementRepository.save(enhancement);
        return megaAttributeRepository.save(megaAttribute);
    }

    public List<Enhancement> getEnhancements(Long megaAttributeId){
        MegaAttribute megaAttribute = megaAttributeRepository.findById(megaAttributeId)
                .orElseThrow(() -> new GameCharNotFoundException(megaAttributeId));

        return megaAttribute.getEnhancements();
    }

    public void deleteEnhancement(Long megaAttributeId, Long enhancementId) {
        MegaAttribute megaAttribute = megaAttributeRepository.findById(megaAttributeId)
                .orElseThrow(() -> new GameCharNotFoundException(megaAttributeId));
        Enhancement enhancement = enhancementRepository.findById(enhancementId)
                .orElseThrow(() -> new GameCharNotFoundException(enhancementId));

        // Delete the enhancement
        megaAttribute.getEnhancements().remove(enhancement);
        enhancementRepository.delete(enhancement);

        // Save the changes
        megaAttributeRepository.save(megaAttribute);
    }

}
