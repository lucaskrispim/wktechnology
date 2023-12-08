package com.example.wktechnology.domain.indicadores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTest {

    @Test
    void testCalcularMedia() {
        List<Double> valores = Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0);

        Media media = new Media(valores);

        double soma = valores.stream().mapToDouble(Double::doubleValue).sum();
        double mediaEsperada = Math.round((soma / valores.size()) * 100.0) / 100.0;

        assertEquals(mediaEsperada, media.calcular());
    }

    @Test
    void testCalcularMediaComListaVazia() {

        List<Double> valoresVazios = new ArrayList<>();


        assertThrows(IllegalArgumentException.class, () -> new Media(valoresVazios));
    }

    @Test
    void testCalcularMediaComValoresNulos() {

        List<Double> valoresNulos = null;


        assertThrows(IllegalArgumentException.class, () -> new Media(valoresNulos));
    }
}
