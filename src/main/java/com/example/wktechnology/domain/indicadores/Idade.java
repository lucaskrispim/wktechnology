package com.example.wktechnology.domain.indicadores;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.DateFormatter;

import java.time.LocalDate;
import java.time.Period;

public class Idade extends Indicador {
    private final Pessoa pessoa;

    public Idade(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public double calcular() {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(pessoa.getDataNasc(), dataAtual);
        return periodo.getYears();
    }

}
