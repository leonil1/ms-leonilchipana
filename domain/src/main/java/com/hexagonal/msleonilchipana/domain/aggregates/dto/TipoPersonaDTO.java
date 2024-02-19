package com.hexagonal.msleonilchipana.domain.aggregates.dto;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class TipoPersonaDTO {
    private Long idTipoPersona;
    private String codTipo;
    private String descTipo;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
}
