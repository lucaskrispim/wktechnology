package com.example.wktechnology.domain.indicadores;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.DateFormatter;

import java.time.LocalDate;
import java.time.Period;

public class Idade {
    private final Pessoa pessoa;

    public Idade(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getIdade() {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(pessoa.getDataNasc(), dataAtual);
        return periodo.getYears();
    }

}
