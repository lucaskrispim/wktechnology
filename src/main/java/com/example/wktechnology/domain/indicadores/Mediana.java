package com.example.wktechnology.domain.indicadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mediana {
    private final List<Double> valores;

    public Mediana(List<Double> valores) {
        if (valores == null || valores.isEmpty()) {
            throw new IllegalArgumentException("A lista de valores não pode estar vazia ou ser nula.");
        }
        this.valores = valores;
    }

    public double calcularMediana() {
        List<Double> valoresOrdenados = new ArrayList<>(valores);
        Collections.sort(valoresOrdenados);

        int tamanho = valoresOrdenados.size();
        if (tamanho % 2 == 0) {
            int meio = tamanho / 2;
            double valor1 = valoresOrdenados.get(meio - 1);
            double valor2 = valoresOrdenados.get(meio);
            return (valor1 + valor2) / 2;
        } else {
            return valoresOrdenados.get(tamanho / 2);
        }
    }
}

