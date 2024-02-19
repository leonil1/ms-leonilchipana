package com.hexagonal.msleonilchipana.domain.ports.out;

import com.hexagonal.msleonilchipana.domain.aggregates.dto.PersonaDTO;
import com.hexagonal.msleonilchipana.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonaDTO cratePersonaOut(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaOut(String numDoc);
    List<PersonaDTO> obtenerTodoOut();
    PersonaDTO actualizarPersonaOut(Long id, RequestPersona requestPersona);
    PersonaDTO deleteOut(Long id);
}
