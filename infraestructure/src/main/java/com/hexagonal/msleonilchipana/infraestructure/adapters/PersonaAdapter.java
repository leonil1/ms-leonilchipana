package com.hexagonal.msleonilchipana.infraestructure.adapters;

import com.hexagonal.msleonilchipana.domain.aggregates.constants.Constants;
import com.hexagonal.msleonilchipana.domain.aggregates.dto.PersonaDTO;
import com.hexagonal.msleonilchipana.domain.aggregates.request.RequestPersona;
import com.hexagonal.msleonilchipana.domain.aggregates.response.ResponseReniec;
import com.hexagonal.msleonilchipana.domain.ports.out.PersonaServiceOut;
import com.hexagonal.msleonilchipana.infraestructure.entity.PersonaEntity;
import com.hexagonal.msleonilchipana.infraestructure.entity.TipoDocumentoEntity;
import com.hexagonal.msleonilchipana.infraestructure.entity.TipoPersonaEntity;
import com.hexagonal.msleonilchipana.infraestructure.mapper.PersonaMaper;
import com.hexagonal.msleonilchipana.infraestructure.repository.PersonaRepository;
import com.hexagonal.msleonilchipana.infraestructure.repository.TipoDocumentoRepository;
import com.hexagonal.msleonilchipana.infraestructure.repository.TipoPersonaRepository;
import com.hexagonal.msleonilchipana.infraestructure.rest.client.ClienteReniec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersona;
    private final PersonaMaper personaMaper;
    private final ClienteReniec reniec;

    @Value("${token.api}")
    private String tokenApi;

    public PersonaAdapter(PersonaRepository personaRepository, TipoDocumentoRepository tipoDocumentoRepository,
                          TipoPersonaRepository tipoPersona, PersonaMaper personaMaper, ClienteReniec reniec) {
        this.personaRepository = personaRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoPersona = tipoPersona;
        this.personaMaper = personaMaper;
        this.reniec = reniec;
    }

    @Override
    public PersonaDTO cratePersonaOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec = getExecutionReniec(requestPersona.getNumDoc());
        personaRepository.save(getEntity(datosReniec, requestPersona));
        return personaMaper.mapToDto(getEntity(datosReniec, requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(String numDoc) {
        return Optional.ofNullable(personaMaper.mapToDto(personaRepository.findByNumDocu(numDoc))) ;
    }

    @Override
    public List<PersonaDTO> obtenerTodoOut() {
        List<PersonaDTO>  personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findAll();
        for (PersonaEntity persona : entities){
            PersonaDTO personaDTO = personaMaper.mapToDto(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public PersonaDTO actualizarPersonaOut(Long id, RequestPersona requestPersona) {
        boolean exist = personaRepository.existsById(id);
        if (exist){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            ResponseReniec responseReniec = getExecutionReniec(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(responseReniec, entity.get()));
            return personaMaper.mapToDto(getEntityUpdate(responseReniec, entity.get()));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean exist = personaRepository.existsById(id);
        if (exist){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMaper.mapToDto(entity.get());
        }
        return null;
    }

    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersonaDoc = tipoPersona.findByCodTipo(requestPersona.getTipoPer());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersonaDoc);
        return entity;
    }

    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity actualizarPersona){
        actualizarPersona.setNombres(reniec.getNombres());
        actualizarPersona.setApePat(reniec.getApellidoPaterno());
        actualizarPersona.setApeMat(reniec.getApellidoMaterno());
        actualizarPersona.setUsuaModif(Constants.AUDIT_ADMIN);
        actualizarPersona.setDateModif(getTimestamp());
        return actualizarPersona;
    }

    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,authorization);
        return  responseReniec;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
