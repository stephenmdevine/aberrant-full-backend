package com.devine.aberrant_character_creator.repository;

import com.devine.aberrant_character_creator.model.Enhancement;
import com.devine.aberrant_character_creator.model.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnhancementRepository extends JpaRepository<Enhancement, Long> {

    Enhancement findByMegaAttributeId(Long megaAttributeId);
}
