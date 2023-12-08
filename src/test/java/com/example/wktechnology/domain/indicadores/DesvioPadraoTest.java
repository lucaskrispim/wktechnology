package com.example.wktechnology.domain.indicadores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DesvioPadraoTest {

    @Test
    void testCalcularDesvioPadrao() {
        // Lista de valores para teste
        List<Double> valores = Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0);

        // Criando um objeto DesvioPadrao com a lista de valores
        DesvioPadrao desvioPadrao = new DesvioPadrao(valores);

        // Calculando o desvio padrão esperado
        Media media = new Media(valores);
        double mediaValor = media.calcular();
        double somaDiferencasQuadradas = 0;
        for (Double valor : valores) {
            somaDiferencasQuadradas += Math.pow(valor - mediaValor, 2);
        }
        double variancia = somaDiferencasQuadradas / valores.size();
        double desvioPadraoEsperado = Math.round((Math.sqrt(variancia)) * 100.0) / 100.0;

        // Verificando se o cálculo do desvio padrão é o mesmo que o esperado
        assertEquals(desvioPadraoEsperado, desvioPadrao.calcular());
    }

    @Test
    void testCalcularDesvioPadraoComListaVazia() {
        // Lista vazia de valores
        List<Double> valoresVazios = new ArrayList<>();

        // Verificando se a criação da classe DesvioPadrao com lista vazia lança a exceção correta
        assertThrows(IllegalArgumentException.class, () -> new DesvioPadrao(valoresVazios));
    }

    @Test
    void testCalcularDesvioPadraoComValoresNulos() {
        // Lista de valores nulos
        List<Double> valoresNulos = null;

        // Verificando se a criação da classe DesvioPadrao com valores nulos lança a exceção correta
        assertThrows(IllegalArgumentException.class, () -> new DesvioPadrao(valoresNulos));
    }
}
