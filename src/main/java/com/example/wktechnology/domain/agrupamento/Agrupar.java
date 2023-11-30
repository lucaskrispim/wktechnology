package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.model.entity.Pessoa;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Agrupar{

    private final List<Pessoa> pessoas;

    public Agrupar(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Map<String, List<Pessoa>> agruparPorFaixaEtaria(){

        return pessoas.stream()
                .collect(Collectors.groupingBy(AgruparPorFaixaEtaria::getFaixaEtaria));
    }

    public Map<String, List<Pessoa>> agruparPorSexo(){

        return pessoas.stream()
                .collect(Collectors.groupingBy(AgruparPorSexo::getSexo));
    }

    public Map<String, List<Pessoa>> agruparPorTipoSanguineo(){

        return pessoas.stream()
                .collect(Collectors.groupingBy(AgruparPorTipoSanguineo::getTipoSanguineo));
    }

}
