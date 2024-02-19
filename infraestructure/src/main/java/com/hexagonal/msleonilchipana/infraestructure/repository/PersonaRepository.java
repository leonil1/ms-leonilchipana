package com.hexagonal.msleonilchipana.infraestructure.repository;

import com.hexagonal.msleonilchipana.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    PersonaEntity findByNumDocu(String numDoc);
}
