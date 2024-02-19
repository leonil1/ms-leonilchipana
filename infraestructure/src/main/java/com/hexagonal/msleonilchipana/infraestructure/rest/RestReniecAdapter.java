package com.hexagonal.msleonilchipana.infraestructure.rest;

import com.hexagonal.msleonilchipana.domain.aggregates.response.ResponseReniec;
import com.hexagonal.msleonilchipana.domain.ports.out.RestReniecOut;
import com.hexagonal.msleonilchipana.infraestructure.rest.client.ClienteReniec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestReniecAdapter implements RestReniecOut {

    private final ClienteReniec reniec;

    public RestReniecAdapter(ClienteReniec reniec) {
        this.reniec = reniec;
    }

    @Value("${token.api}")
    private String tokenApi;
    @Override
    public ResponseReniec getInfoReniec(String numDoc) {
        String authorization = "Bearer " + tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numDoc, authorization);
        return responseReniec;
    }
}
