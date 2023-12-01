package com.example.wktechnology.domain.indicadores;

import java.util.List;

public class Media extends Indicador {
    private final List<Double> valores;

    public Media(List<Double> valores) {
        if (valores == null || valores.isEmpty()) {
            throw new IllegalArgumentException("A lista de valores não pode estar vazia ou ser nula.");
        }
        this.valores = valores;
    }

    public double calcular() {
        double soma = 0;
        for (Double valor : valores) {
            soma += valor;
        }

        return Math.round( (soma / valores.size()) * 100.0) / 100.0;
    }
}
