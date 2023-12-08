package com.example.wktechnology.domain.indicadores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MedianaTest {

    @Test
    void testCalcularMedianaListaImpar() {
        List<Double> valoresImpares = Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0);

        Mediana medianaImpares = new Mediana(valoresImpares);

        double[] arrayImpares = valoresImpares.stream().mapToDouble(Double::doubleValue).toArray();
        Arrays.sort(arrayImpares);
        double medianaEsperadaImpares = arrayImpares[arrayImpares.length / 2];

        assertEquals(medianaEsperadaImpares, medianaImpares.calcular());
    }

    @Test
    void testCalcularMedianaListaPar() {
        List<Double> valoresPares = Arrays.asList(15.0, 25.0, 35.0, 45.0);

        Mediana medianaPares = new Mediana(valoresPares);

        double[] arrayPares = valoresPares.stream().mapToDouble(Double::doubleValue).toArray();
        Arrays.sort(arrayPares);
        double valor1 = arrayPares[arrayPares.length / 2 - 1];
        double valor2 = arrayPares[arrayPares.length / 2];
        double medianaEsperadaPares = (valor1 + valor2) / 2;

        assertEquals(medianaEsperadaPares, medianaPares.calcular());
    }

    @Test
    void testCalcularMedianaListaVazia() {
        List<Double> valoresVazios = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> new Mediana(valoresVazios));
    }

    @Test
    void testCalcularMedianaComValoresNulos() {
        List<Double> valoresNulos = null;

        assertThrows(IllegalArgumentException.class, () -> new Mediana(valoresNulos));
    }
}
