package com.example.wktechnology.domain.calculo;

import com.example.wktechnology.model.entity.Pessoa;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {

    @Test
    void testGetMediaDeIdadePorGrupoComListaVazia() {
        Calc calc = new Calc(new HashMap<>());

        Map<String, Double> resultado = calc.getMediaDeIdadePorGrupo();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testGetMediaDeIdadePorGrupoComPessoas() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setDataNasc(LocalDate.of(1995, 1, 1));
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setDataNasc(LocalDate.of(1990, 6, 15));


        Map<String, List<Pessoa>> pessoasAgrupadas = new HashMap<>();
        pessoasAgrupadas.put("A", Arrays.asList(pessoa1, pessoa2));


        Calc calc = new Calc(pessoasAgrupadas);


        Map<String, Double> resultado = calc.getMediaDeIdadePorGrupo();


        assertEquals(30.5, resultado.get("A")); // A idade é calculada em anos completos
    }

    @Test
    void testGetImcMedioPorGrupoDePessoasComPessoas() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setPeso(70);
        pessoa1.setAltura(1.75);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setPeso(65);
        pessoa2.setAltura(1.68);

        Map<String, List<Pessoa>> pessoasAgrupadas = new HashMap<>();
        pessoasAgrupadas.put("Adultos", Arrays.asList(pessoa1, pessoa2));


        Calc calc = new Calc(pessoasAgrupadas);


        Map<String, Double> resultado = calc.getImcMedioPorGrupoDePessoas(pessoasAgrupadas);


        double imcPessoa1 = pessoa1.getPeso() / (pessoa1.getAltura() * pessoa1.getAltura());
        double imcPessoa2 = pessoa2.getPeso() / (pessoa2.getAltura() * pessoa2.getAltura());
        double mediaEsperada = (imcPessoa1 + imcPessoa2) / 2;


        assertEquals(mediaEsperada, resultado.get("Adultos"), 0.01);
    }

    @Test
    void testGetImcMedioPorGrupoDePessoasMaiorQueComPessoas() {

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setPeso(70);
        pessoa1.setAltura(1.75);

        Pessoa pessoa2 = new Pessoa();

        pessoa2.setPeso(65);
        pessoa2.setAltura(1.68);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setPeso(90);
        pessoa3.setAltura(1.80);

        Map<String, List<Pessoa>> pessoasAgrupadas = new HashMap<>();
        pessoasAgrupadas.put("Adultos", Arrays.asList(pessoa1, pessoa2, pessoa3));

        Calc calc = new Calc(pessoasAgrupadas);

        double valorLimite = 25.0;
        Map<String, Double> resultado = calc.getImcMedioPorGrupoDePessoasMaiorQue(pessoasAgrupadas, valorLimite);

        double imcPessoa1 = pessoa1.getPeso() / (pessoa1.getAltura() * pessoa1.getAltura());
        double imcPessoa2 = pessoa2.getPeso() / (pessoa2.getAltura() * pessoa2.getAltura());
        double imcPessoa3 = pessoa3.getPeso() / (pessoa3.getAltura() * pessoa3.getAltura());

        List<Double> imcMaioresQue25 = new ArrayList<>();
        if (imcPessoa1 > valorLimite) {
            imcMaioresQue25.add(imcPessoa1);
        }
        if (imcPessoa2 > valorLimite) {
            imcMaioresQue25.add(imcPessoa2);
        }
        if (imcPessoa3 > valorLimite) {
            imcMaioresQue25.add(imcPessoa3);
        }

        double mediaEsperada = imcMaioresQue25.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        assertEquals(mediaEsperada, resultado.get("Adultos"), 0.01); // Tolerância de 0.01 para lidar com imprecisões de ponto flutuante
    }
}

