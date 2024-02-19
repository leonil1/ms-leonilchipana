package com.hexagonal.msleonilchipana.domain.ports.out;

import com.hexagonal.msleonilchipana.domain.aggregates.response.ResponseReniec;

public interface RestReniecOut {
    ResponseReniec getInfoReniec(String numDoc);
}
