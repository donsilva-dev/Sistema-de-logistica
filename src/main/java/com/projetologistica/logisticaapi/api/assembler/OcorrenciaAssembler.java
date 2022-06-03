package com.projetologistica.logisticaapi.api.assembler;

import com.projetologistica.logisticaapi.domain.model.Ocorrencia;
import com.projetologistica.logisticaapi.domain.model.OcorrenciaModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OcorrenciaAssembler<toCollectionModel> {

    private ModelMapper modelMapper;

    public OcorrenciaAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {

        return modelMapper.map(ocorrencia, OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
