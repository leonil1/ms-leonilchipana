package com.hexagonal.msleonilchipana.application.controller;

import com.hexagonal.msleonilchipana.domain.aggregates.dto.PersonaDTO;
import com.hexagonal.msleonilchipana.domain.aggregates.request.RequestPersona;
import com.hexagonal.msleonilchipana.domain.ports.in.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "API-PERSONA",
                version = "v1.1.0",
                description = "Matenimiento de una Persona"
        )
)

@RestController
@RequestMapping("/v2/persona")
public class ControllerPersona {

    private final PersonaServiceIn personaServiceIn;

    public ControllerPersona(PersonaServiceIn personaServiceIn) {
        this.personaServiceIn = personaServiceIn;
    }

    @Operation(summary = "Registrar una Persona")
    @PostMapping("/create")
    public ResponseEntity<PersonaDTO> registro(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.cratePersonaIn(requestPersona));
    }

    @Operation(summary = "Obtener una persona por DNI")
    @GetMapping("/{numDoc}")
    public ResponseEntity<PersonaDTO> obtenerPorId(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(numDoc).get());
    }

    @Operation(summary = "Obtener Toda las Personas")
    @GetMapping("/lista")
    public ResponseEntity<List<PersonaDTO>> obtenerTodo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodoIn());
    }

    @Operation(summary = "Actualizar una Persona")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizar(@PathVariable Long id, RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarPersonaIn(id, requestPersona));
    }

    @Operation(summary = "Desactivas unaPersona")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDTO> eliminar(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.deleteIn(id));
    }

}
