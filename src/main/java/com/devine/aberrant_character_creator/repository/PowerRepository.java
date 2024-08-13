package com.devine.aberrant_character_creator.repository;

import com.devine.aberrant_character_creator.model.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerRepository extends JpaRepository<Power, Long> {
}
