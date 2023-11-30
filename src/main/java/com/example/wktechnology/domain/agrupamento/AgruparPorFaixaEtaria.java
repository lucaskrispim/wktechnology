package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.domain.indicadores.Idade;
import com.example.wktechnology.model.entity.Pessoa;

public class AgruparPorFaixaEtaria {

    public static String getFaixaEtaria(Pessoa pessoa) {

        Idade idade = new Idade(pessoa);
        int idadeValor = idade.getIdade();
        int faixaEtaria = idadeValor / 10 * 10;
        return faixaEtaria + "-" + (faixaEtaria + 9);
    }
}
