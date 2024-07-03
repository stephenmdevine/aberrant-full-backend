package com.devine.aberrant_character_creator.service;

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
                    if (updateDTO.getPlayer() != null) {
                        gameChar.setPlayer(updateDTO.getPlayer());
                    }
                    if (updateDTO.getName() != null) {
                        gameChar.setName(updateDTO.getName());
                    }
                    if (updateDTO.getNovaName() != null) {
                        gameChar.setNovaName(updateDTO.getNovaName());
                    }
                    return gameCharRepository.save(gameChar);
                })
                .orElseThrow(() -> new GameCharNotFoundException(id));
    }

}
