package com.hexagonal.msleonilchipana.domain.ports.in;

import com.hexagonal.msleonilchipana.domain.aggregates.dto.PersonaDTO;
import com.hexagonal.msleonilchipana.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO cratePersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaIn(String numDoc);
    List<PersonaDTO> obtenerTodoIn();
    PersonaDTO actualizarPersonaIn(Long id, RequestPersona requestPersona);
    PersonaDTO deleteIn(Long id);
}
