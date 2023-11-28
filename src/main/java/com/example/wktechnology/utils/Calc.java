package com.example.wktechnology.utils;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.TipoSanguineo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calc {

    public static Map<String, Double> getIdadeMediaPorTipoSanguineo(Map<String, List<Pessoa>> pessoasPorTipoSanguineo){
        Map<String, Double> idadeMediPorTipoSanguineo = new HashMap<>();
        pessoasPorTipoSanguineo.forEach((tipoSanguineo, listaPessoas) -> {
            double idadeTotal = 0.0;
            for (Pessoa pessoa : listaPessoas) {
                idadeTotal += Agrupar.getIdade(pessoa.getDataNasc());
            }
            double idadeMedia = idadeTotal / listaPessoas.size();
            idadeMediPorTipoSanguineo.put(tipoSanguineo, Math.round(idadeMedia * 100.0) / 100.0);
        });

        return idadeMediPorTipoSanguineo;
    }
}
