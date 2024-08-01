package com.devine.aberrant_character_creator.repository;

import com.devine.aberrant_character_creator.model.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Long> {

    Quality findByAttributeId(Long attributeId);
}
