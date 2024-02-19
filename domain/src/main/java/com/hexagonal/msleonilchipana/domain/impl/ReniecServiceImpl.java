package com.hexagonal.msleonilchipana.domain.impl;

import com.hexagonal.msleonilchipana.domain.aggregates.response.ResponseReniec;
import com.hexagonal.msleonilchipana.domain.ports.in.ReniecServiceIn;
import com.hexagonal.msleonilchipana.domain.ports.out.RestReniecOut;
import org.springframework.stereotype.Service;

@Service
public class ReniecServiceImpl implements ReniecServiceIn {

    private final RestReniecOut reniecServiceOut;

    public ReniecServiceImpl(RestReniecOut reniecServiceOut) {
        this.reniecServiceOut = reniecServiceOut;
    }

    @Override
    public ResponseReniec getInfoIn(String numero) {
        return reniecServiceOut.getInfoReniec(numero);
    }
}
