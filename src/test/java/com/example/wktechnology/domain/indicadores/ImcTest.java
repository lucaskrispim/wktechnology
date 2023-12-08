package com.example.wktechnology.domain.indicadores;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImcTest {

    @Test
    void testConstructorValidInputs() {
        // Teste de construtor com valores válidos
        assertDoesNotThrow(() -> new Imc(70, 1.75));
    }

    @Test
    void testConstructorInvalidInputs() {
        // Teste de construtor com valores inválidos
        assertThrows(IllegalArgumentException.class, () -> new Imc(-70, 1.75));
        assertThrows(IllegalArgumentException.class, () -> new Imc(70, -1.75));
        assertThrows(IllegalArgumentException.class, () -> new Imc(0, 1.75));
        assertThrows(IllegalArgumentException.class, () -> new Imc(70, 0));
    }

    @Test
    void testCalcular() {
        // Teste do método calcular
        Imc imc = new Imc(70, 1.75);
        assertEquals(22.86, imc.calcular());
    }

    @Test
    void testClassificarMagrezaGrave() {
        Imc imc = new Imc(15.9 * 1.75 * 1.75, 1.75);
        assertEquals("Magreza grave", imc.classificarIMC());
    }

    @Test
    void testClassificarMagrezaModerada() {
        Imc imc = new Imc(16.9 * 1.75 * 1.75, 1.75);
        assertEquals("Magreza moderada", imc.classificarIMC());
    }

    @Test
    void testClassificarMagrezaLeve() {
        Imc imc = new Imc(18.4 * 1.75 * 1.75, 1.75);
        assertEquals("Magreza leve", imc.classificarIMC());
    }

    @Test
    void testClassificarPesoSaudavel() {
        Imc imc = new Imc(24.9 * 1.75 * 1.75, 1.75);
        assertEquals("Peso saudável", imc.classificarIMC());
    }

    @Test
    void testClassificarSobrepeso() {
        Imc imc = new Imc(29.9 * 1.75 * 1.75, 1.75);
        assertEquals("Sobrepeso", imc.classificarIMC());
    }

    @Test
    void testClassificarObesidadeGraulUm() {
        Imc imc = new Imc(34.9 * 1.75 * 1.75, 1.75);
        assertEquals("Obesidade grau I", imc.classificarIMC());
    }

    @Test
    void testClassificarObesidadeGraulDois() {
        Imc imc = new Imc(39.9 * 1.75 * 1.75, 1.75);
        assertEquals("Obesidade grau II (severa)", imc.classificarIMC());
    }

    @Test
    void testClassificarObesidadeGraulTres() {
        Imc imc = new Imc(49.9 * 1.75 * 1.75, 1.75);
        assertEquals("Obesidade grau III (mórbida)", imc.classificarIMC());
    }

    @Test
    void testClassificarObesidadeGraulQuatro() {
        Imc imc = new Imc(59.9 * 1.75 * 1.75, 1.75);
        assertEquals("Obesidade grau IV (extrema)", imc.classificarIMC());
    }

    @Test
    void testClassificarObesidadeGraulCinco() {
        Imc imc = new Imc(60.1 * 1.75 * 1.75, 1.75);
        assertEquals("Obesidade grau V (supermórbida)", imc.classificarIMC());
    }

    @Test
    void testGettersAndSetters() {
        // Teste dos métodos getters e setters
        Imc imc = new Imc(70, 1.75);
        imc.setAltura(1.80);
        imc.setPeso(75);
        assertEquals(1.80, imc.getAltura());
        assertEquals(75, imc.getPeso());
    }
}