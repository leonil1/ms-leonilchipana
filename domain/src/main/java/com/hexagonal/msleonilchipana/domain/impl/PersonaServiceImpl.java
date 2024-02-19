package com.hexagonal.msleonilchipana.domain.impl;

import com.hexagonal.msleonilchipana.domain.aggregates.dto.PersonaDTO;
import com.hexagonal.msleonilchipana.domain.aggregates.request.RequestPersona;
import com.hexagonal.msleonilchipana.domain.ports.in.PersonaServiceIn;
import com.hexagonal.msleonilchipana.domain.ports.out.PersonaServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    public PersonaServiceImpl(PersonaServiceOut personaServiceOut) {
        this.personaServiceOut = personaServiceOut;
    }

    @Override
    public PersonaDTO cratePersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.cratePersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(String numDoc) {
        return personaServiceOut.obtenerPersonaOut(numDoc);
    }

    @Override
    public List<PersonaDTO> obtenerTodoIn() {
        return personaServiceOut.obtenerTodoOut();
    }

    @Override
    public PersonaDTO actualizarPersonaIn(Long id, RequestPersona requestPersona) {
        return personaServiceOut.actualizarPersonaOut(id, requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }
}
