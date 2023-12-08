package com.example.wktechnology.domain.indicadores;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wktechnology.model.entity.Pessoa;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Period;
import static org.junit.jupiter.api.Assertions.*;

public class IdadeTest {

    @Test
    void testCalcularIdade() {
        // Criando uma data de nascimento
        LocalDate dataNascimento = LocalDate.of(1990, 5, 15);

        // Criando uma pessoa com a data de nascimento definida
        Pessoa pessoa = new Pessoa();
        pessoa.setDataNasc(dataNascimento);

        // Criando um objeto Idade com a pessoa criada
        Idade idade = new Idade(pessoa);

        // Calculando a idade esperada
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        double idadeEsperada = periodo.getYears();

        // Verificando se o cálculo da idade é o mesmo que o esperado
        assertEquals(idadeEsperada, idade.calcular());
    }

    @Test
    void testCalcularIdadeComAniversarioHoje() {
        // Criando uma data de nascimento igual à data atual
        LocalDate dataAtual = LocalDate.now();

        // Criando uma pessoa com a data de nascimento definida como a data atual
        Pessoa pessoa = new Pessoa();
        pessoa.setDataNasc(dataAtual);

        // Criando um objeto Idade com a pessoa criada
        Idade idade = new Idade(pessoa);

        // Para essa situação, a idade esperada é 0, pois é o aniversário de hoje
        double idadeEsperada = 0;

        // Verificando se o cálculo da idade é o mesmo que o esperado
        assertEquals(idadeEsperada, idade.calcular());
    }
}
