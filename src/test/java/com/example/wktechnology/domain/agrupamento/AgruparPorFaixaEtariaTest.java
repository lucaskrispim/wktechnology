package com.example.wktechnology.domain.agrupamento;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wktechnology.model.entity.Pessoa;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AgruparPorFaixaEtariaTest {

    @Test
    void testAgruparPorFaixaEtaria() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setDataNasc(LocalDate.of(1990, 1, 1));
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setDataNasc(LocalDate.of(1985, 6, 15));

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);


        Agrupar agruparMock = Mockito.mock(Agrupar.class);
        Mockito.when(agruparMock.agruparPorFaixaEtaria()).thenReturn(Map.of("30-39", pessoas));


        AgruparPorFaixaEtaria agrupador = new AgruparPorFaixaEtaria(agruparMock);


        Map<String, List<Pessoa>> resultado = agrupador.agrupar();


        assertTrue(resultado.containsKey("30-39"));
        assertEquals(2, resultado.get("30-39").size());
    }

    @Test
    void testGetFaixaEtaria() {

        Pessoa pessoa = new Pessoa();
        pessoa.setDataNasc(LocalDate.of(1988, 5, 10));

        String faixaEtaria = AgruparPorFaixaEtaria.getFaixaEtaria(pessoa);

        assertEquals("30-39", faixaEtaria);
    }
}
