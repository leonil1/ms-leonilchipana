package com.hexagonal.msleonilchipana.infraestructure.mapper;

import com.hexagonal.msleonilchipana.domain.aggregates.dto.PersonaDTO;
import com.hexagonal.msleonilchipana.infraestructure.entity.PersonaEntity;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaMaper {

    private static final ModelMapper modelMaper = new ModelMapper();

    public PersonaDTO mapToDto(PersonaEntity entity){
        return modelMaper.map(entity, PersonaDTO.class);
    }

    public PersonaEntity mapTpEntity(PersonaDTO personaDTO){
        return modelMaper.map(personaDTO, PersonaEntity.class);
    }
}
