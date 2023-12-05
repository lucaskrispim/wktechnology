package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.model.entity.Pessoa;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgruparPorTipoSanguineo {

    private Agrupar agrupar;

    public AgruparPorTipoSanguineo(Agrupar agrupar) {
        this.agrupar = agrupar;
    }

    public Map<String, List<Pessoa>> agrupar(){

        return agrupar.agruparPorTipoSanguineo();
    }

    public static String getTipoSanguineo(Pessoa pessoa){

        return pessoa.getTipoSanguineo();
    }
}
