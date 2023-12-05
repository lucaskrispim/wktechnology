package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.domain.indicadores.Idade;
import com.example.wktechnology.model.entity.Pessoa;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgruparPorFaixaEtaria {

    private Agrupar agrupar;

    public AgruparPorFaixaEtaria(Agrupar agrupar){
        this.agrupar = agrupar;
    }

    public Map<String, List<Pessoa>> agrupar(){
        return agrupar.agruparPorFaixaEtaria();
    }

    public static String getFaixaEtaria(Pessoa pessoa) {

        Idade idade = new Idade(pessoa);
        double idadeValor = idade.calcular();
        int faixaEtaria = ((int) idadeValor) / 10 * 10;
        return faixaEtaria + "-" + (faixaEtaria + 9);
    }
}
