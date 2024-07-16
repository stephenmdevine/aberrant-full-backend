package com.devine.aberrant_character_creator.repository;

import com.devine.aberrant_character_creator.model.Flaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlawRepository extends JpaRepository<Flaw, Long> {
}
