package com.example.wktechnology.domain.agrupamento;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AgruparPorTipoSanguineoTest {

    @Test
    void testAgruparPorTipoSanguineo() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);

        Agrupar agruparMock = Mockito.mock(Agrupar.class);
        Mockito.when(agruparMock.agruparPorTipoSanguineo()).thenReturn(Map.of("A+", Collections.singletonList(pessoa1),
                "B-", Collections.singletonList(pessoa2)));

        AgruparPorTipoSanguineo agrupador = new AgruparPorTipoSanguineo(agruparMock);

        Map<String, List<Pessoa>> resultado = agrupador.agrupar();

        assertTrue(resultado.containsKey("A+"));
        assertTrue(resultado.containsKey("B-"));
        assertEquals(1, resultado.get("A+").size());
        assertEquals(1, resultado.get("B-").size());
    }

    @Test
    void testGetTipoSanguineo() {
        Pessoa pessoa = new Pessoa();
        pessoa.setTipoSanguineo(TipoSanguineo.O_POSITIVO);

        String tipoSanguineo = AgruparPorTipoSanguineo.getTipoSanguineo(pessoa);

        assertEquals("O+", tipoSanguineo);
    }
}
