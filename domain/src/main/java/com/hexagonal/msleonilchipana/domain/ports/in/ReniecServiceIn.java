package com.hexagonal.msleonilchipana.domain.ports.in;

import com.hexagonal.msleonilchipana.domain.aggregates.response.ResponseReniec;

public interface ReniecServiceIn {
    ResponseReniec getInfoIn(String numero);
}
