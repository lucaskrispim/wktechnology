package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgruparTest {

    @Test
    void testAgruparPessoasPorFaixaEtaria() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setDataNasc(LocalDate.of(1990, 1, 1));
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setDataNasc(LocalDate.of(1985, 6, 15));

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);

        Agrupar agrupador = new Agrupar(pessoas);

        Map<String, List<Pessoa>> resultado = agrupador.agruparPorFaixaEtaria();

        assertEquals(2, resultado.get("30-39").size()); // Ambas as pessoas devem estar nessa faixa etária
    }

    @Test
    void testAgruparPessoasPorSexo() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setSexo(Sexo.MASCULINO);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setSexo(Sexo.FEMININO);

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);

        Agrupar agrupador = new Agrupar(pessoas);

        Map<String, List<Pessoa>> resultado = agrupador.agruparPorSexo();
        assertEquals(1, resultado.get("Masculino").size()); // Apenas uma pessoa masculina na lista
        assertEquals(1, resultado.get("Feminino").size()); // Apenas uma pessoa feminina na lista
    }

    @Test
    void testAgruparPessoasPorTipoSanguineo() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);

        Agrupar agrupador = new Agrupar(pessoas);

        Map<String, List<Pessoa>> resultado = agrupador.agruparPorTipoSanguineo();

        assertEquals(1, resultado.get("A+").size()); // Apenas uma pessoa com tipo sanguíneo A+
        assertEquals(1, resultado.get("B-").size()); // Apenas uma pessoa com tipo sanguíneo B-
    }

}
