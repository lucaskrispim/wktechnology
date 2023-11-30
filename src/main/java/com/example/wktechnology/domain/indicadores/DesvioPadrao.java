package com.example.wktechnology.domain.indicadores;

import java.util.List;

public class DesvioPadrao {
    private final List<Double> valores;

    public DesvioPadrao(List<Double> valores) {
        if (valores == null || valores.isEmpty()) {
            throw new IllegalArgumentException("A lista de valores não pode estar vazia ou ser nula.");
        }
        this.valores = valores;
    }

    public double calcularDesvioPadrao() {
        Media media = new Media(valores);
        double mediaValor = media.getMedia();
        double somaDiferencasQuadradas = 0;

        for (Double valor : valores) {
            somaDiferencasQuadradas += Math.pow(valor - mediaValor, 2);
        }

        double variancia = somaDiferencasQuadradas / valores.size();

        return Math.round( (Math.sqrt(variancia)) * 100.0) / 100.0;
    }


}
