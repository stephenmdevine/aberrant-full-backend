package com.devine.aberrant_character_creator.service;

import com.devine.aberrant_character_creator.dto.PowerDTO;
import com.devine.aberrant_character_creator.model.Power;
import com.devine.aberrant_character_creator.repository.PowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerService {

    @Autowired
    private PowerRepository powerRepository;

    public Power createPower(PowerDTO powerDTO) {
        Power power = new Power();
        power.setName(powerDTO.getName());
        power.setValue(powerDTO.getValue());
        power.setLevel(powerDTO.getLevel());
        power.setQuantumMinimum(powerDTO.getQuantumMinimum());
        power.setHasExtra(powerDTO.isHasExtra());
        power.setExtraName(powerDTO.getExtraName());

        return powerRepository.save(power);
    }

}
