package com.example.wktechnology.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompatibilidadeSanguineaTest {

    @Test
    void testDoadoresParaGrupoDeReceptor() {

        Map<String, List<Pessoa>> pessoasPorTipoSanguineo = new HashMap<>();

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Ana");
        pessoa1.setTipoSanguineo(TipoSanguineo.A_POSITIVO);

        Pessoa pessoa2 = new Pessoa();

        pessoa2.setNome("João");
        pessoa2.setTipoSanguineo(TipoSanguineo.A_POSITIVO);


        Pessoa pessoa3 = new Pessoa();

        pessoa3.setNome("Maria");
        pessoa3.setTipoSanguineo(TipoSanguineo.O_NEGATIVO);


        Pessoa pessoa4 = new Pessoa();

        pessoa4.setNome("Pedro");
        pessoa4.setTipoSanguineo(TipoSanguineo.O_NEGATIVO);

        pessoasPorTipoSanguineo.put(TipoSanguineo.A_POSITIVO.getDescricao(), Arrays.asList(
                pessoa1, pessoa2
        ));

        pessoasPorTipoSanguineo.put(TipoSanguineo.O_NEGATIVO.getDescricao(), Arrays.asList(
                pessoa3,
                pessoa4
        ));

        CompatibilidadeSanguinea compatibilidade = new CompatibilidadeSanguinea();


        Map<String, List<Pessoa>> doadoresParaReceptor = compatibilidade.doadoresParaGrupoDeReceptor(pessoasPorTipoSanguineo);


        assertEquals(2, doadoresParaReceptor.get(TipoSanguineo.A_POSITIVO.getDescricao()).size());
        assertEquals(2, doadoresParaReceptor.get(TipoSanguineo.O_NEGATIVO.getDescricao()).size());
    }

    @Test
    void testContagemDeDoadoresParaGrupoDeReceptor() {

        Map<String, List<Pessoa>> pessoasPorTipoSanguineo = new HashMap<>();

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Carlos");
        pessoa1.setTipoSanguineo(TipoSanguineo.B_NEGATIVO);

        Pessoa pessoa2 = new Pessoa();

        pessoa2.setNome("Camila");
        pessoa2.setTipoSanguineo(TipoSanguineo.O_POSITIVO);


        Pessoa pessoa3 = new Pessoa();

        pessoa3.setNome("Rafael");
        pessoa3.setTipoSanguineo(TipoSanguineo.O_POSITIVO);

        pessoasPorTipoSanguineo.put(TipoSanguineo.B_NEGATIVO.getDescricao(), Collections.singletonList(
                pessoa1
        ));

        pessoasPorTipoSanguineo.put(TipoSanguineo.O_POSITIVO.getDescricao(), Arrays.asList(
                pessoa2,
                pessoa3
        ));

        CompatibilidadeSanguinea compatibilidade = new CompatibilidadeSanguinea();


        Map<String, Integer> quantidadeDoadoresPorReceptor = compatibilidade.contagemDeDoadoresParaGrupoDeReceptor(pessoasPorTipoSanguineo);


        assertEquals(1, quantidadeDoadoresPorReceptor.get(TipoSanguineo.B_NEGATIVO.getDescricao()));
        assertEquals(2, quantidadeDoadoresPorReceptor.get(TipoSanguineo.O_POSITIVO.getDescricao()));
    }

    @Test
    void testToString() {
        CompatibilidadeSanguinea compatibilidade = new CompatibilidadeSanguinea();

        String expectedOutput = "Tipo Sanguíneo: B+\n" +
                "  Pode Doar Para: [B+, AB+]\n" +
                "  Pode Receber De: [B+, B-, O+, O-]\n" +
                "\n" +
                "Tipo Sanguíneo: A+\n" +
                "  Pode Doar Para: [A+, AB+]\n" +
                "  Pode Receber De: [A+, A-, O+, O-]\n" +
                "\n" +
                "Tipo Sanguíneo: AB+\n" +
                "  Pode Doar Para: [AB+]\n" +
                "  Pode Receber De: [A+, B+, O+, AB+, A-, B-, O-, AB-]\n" +
                "\n" +
                "Tipo Sanguíneo: B-\n" +
                "  Pode Doar Para: [B+, B-, AB+, AB-]\n" +
                "  Pode Receber De: [B-, O-]\n" +
                "\n" +
                "Tipo Sanguíneo: A-\n" +
                "  Pode Doar Para: [A+, A-, AB+, AB-]\n" +
                "  Pode Receber De: [A-, O-]\n" +
                "\n" +
                "Tipo Sanguíneo: AB-\n" +
                "  Pode Doar Para: [AB+, AB-]\n" +
                "  Pode Receber De: [A-, B-, O-, AB-]\n" +
                "\n" +
                "Tipo Sanguíneo: O+\n" +
                "  Pode Doar Para: [A+, B+, O+, AB+]\n" +
                "  Pode Receber De: [O+, O-]\n" +
                "\n" +
                "Tipo Sanguíneo: O-\n" +
                "  Pode Doar Para: [A+, B+, O+, AB+, A-, B-, O-, AB-]\n" +
                "  Pode Receber De: [O-]\n\n";

        String toStringOutput = compatibilidade.toString();

        assertEquals(expectedOutput, toStringOutput);
    }
}
