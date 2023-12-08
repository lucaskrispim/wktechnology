package com.example.wktechnology.model.entity;

import com.example.wktechnology.utils.enums.EstadoBrasileiro;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {

    @Test
    void testToString() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setCpf("12345678900");
        pessoa.setRg("1234567");
        pessoa.setDataNasc(LocalDate.of(1990, 5, 15));
        pessoa.setSexo(Sexo.MASCULINO);
        pessoa.setMae("Maria");
        pessoa.setPai("José");
        pessoa.setEmail("joao@gmail.com");
        pessoa.setCep("12345678");
        pessoa.setEndereco("Rua ABC");
        pessoa.setNumero(100);
        pessoa.setBairro("Centro");
        pessoa.setCidade("São Paulo");
        pessoa.setEstado(EstadoBrasileiro.SP);
        pessoa.setTelefoneFixo("1123456789");
        pessoa.setCelular("11987654321");
        pessoa.setAltura(1.75);
        pessoa.setPeso(70);
        pessoa.setTipoSanguineo(TipoSanguineo.A_POSITIVO);

        String expectedToString = "Pessoa{" +
                "id=1, " +
                "nome='João', " +
                "cpf='12345678900', " +
                "rg='1234567', " +
                "dataNasc='1990-05-15', " +
                "sexo='MASCULINO', " +
                "mae='Maria', " +
                "pai='José', " +
                "email='joao@gmail.com', " +
                "cep='12345678', " +
                "endereco='Rua ABC', " +
                "numero=100, " +
                "bairro='Centro', " +
                "cidade='São Paulo', " +
                "estado='SP', " +
                "telefoneFixo='1123456789', " +
                "celular='11987654321', " +
                "altura=1.75, " +
                "peso=70, " +
                "tipoSanguineo='A_POSITIVO'}";

        assertEquals(expectedToString, pessoa.toString());
    }

    @Test
    void testGetters() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setCpf("12345678900");
        pessoa.setRg("1234567");
        pessoa.setDataNasc(LocalDate.of(1990, 5, 15));
        pessoa.setSexo(Sexo.MASCULINO);
        pessoa.setMae("Maria");
        pessoa.setPai("José");
        pessoa.setEmail("joao@gmail.com");
        pessoa.setCep("12345678");
        pessoa.setEndereco("Rua ABC");
        pessoa.setNumero(100);
        pessoa.setBairro("Centro");
        pessoa.setCidade("São Paulo");
        pessoa.setEstado(EstadoBrasileiro.SP);
        pessoa.setTelefoneFixo("1123456789");
        pessoa.setCelular("11987654321");
        pessoa.setAltura(1.75);
        pessoa.setPeso(70);
        pessoa.setTipoSanguineo(TipoSanguineo.A_POSITIVO);

        assertEquals(1L, pessoa.getId());
        assertEquals("João", pessoa.getNome());
        assertEquals("12345678900", pessoa.getCpf());
        assertEquals("1234567", pessoa.getRg());
        assertEquals(LocalDate.of(1990, 5, 15), pessoa.getDataNasc());
        assertEquals("Masculino", pessoa.getSexo());
        assertEquals("Maria", pessoa.getMae());
        assertEquals("José", pessoa.getPai());
        assertEquals("joao@gmail.com", pessoa.getEmail());
        assertEquals("12345678", pessoa.getCep());
        assertEquals("Rua ABC", pessoa.getEndereco());
        assertEquals(100, pessoa.getNumero());
        assertEquals("Centro", pessoa.getBairro());
        assertEquals("São Paulo", pessoa.getCidade());
        assertEquals(EstadoBrasileiro.SP, pessoa.getEstado());
        assertEquals("1123456789", pessoa.getTelefoneFixo());
        assertEquals("11987654321", pessoa.getCelular());
        assertEquals(1.75, pessoa.getAltura());
        assertEquals(70, pessoa.getPeso());
        assertEquals("A+", pessoa.getTipoSanguineo());
    }
}
