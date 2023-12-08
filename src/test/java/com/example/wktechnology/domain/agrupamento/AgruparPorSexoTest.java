package com.example.wktechnology.domain.agrupamento;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AgruparPorSexoTest {

    @Test
    void testAgruparPorSexo() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setSexo(Sexo.MASCULINO);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setSexo(Sexo.FEMININO);

        Agrupar agruparMock = Mockito.mock(Agrupar.class);
        Mockito.when(agruparMock.agruparPorSexo()).thenReturn(Map.of("Masculino", Collections.singletonList(pessoa1),
                "Feminino", Collections.singletonList(pessoa2)));

        AgruparPorSexo agrupador = new AgruparPorSexo(agruparMock);

        Map<String, List<Pessoa>> resultado = agrupador.agrupar();

        assertTrue(resultado.containsKey("Masculino"));
        assertTrue(resultado.containsKey("Feminino"));
        assertEquals(1, resultado.get("Masculino").size());
        assertEquals(1, resultado.get("Feminino").size());
    }

    @Test
    void testGetSexo() {

        Pessoa pessoa = new Pessoa();
        pessoa.setSexo(Sexo.MASCULINO);

        String sexo = AgruparPorSexo.getSexo(pessoa);

        assertEquals("Masculino", sexo);
    }
}
