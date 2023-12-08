package com.example.wktechnology.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wktechnology.domain.agrupamento.Agrupar;
import com.example.wktechnology.domain.agrupamento.AgruparPorFaixaEtaria;
import com.example.wktechnology.domain.agrupamento.AgruparPorSexo;
import com.example.wktechnology.domain.agrupamento.AgruparPorTipoSanguineo;
import com.example.wktechnology.domain.calculo.Calc;
import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.example.wktechnology.model.repository.PessoaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void testListarTodasAsPessoasEmPagina() {

        List<Pessoa> pessoas = new ArrayList<>();

        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Ana");
        pessoas.add(pessoa1);

        Pessoa pessoa2 = new Pessoa();

        pessoa2.setNome("Bob");

        pessoas.add(pessoa2);



        int offset = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        Page<Pessoa> paginaPessoas = new PageImpl<>(pessoas, pageRequest, pessoas.size());

        when(pessoaRepository.findAll(pageRequest)).thenReturn(paginaPessoas);


        Page<Pessoa> resultado = pessoaService.listarTodasAsPessoas(offset, pageSize);


        assertEquals(pessoas.size(), resultado.getContent().size());

    }

    @Test
    void testListarTodasAsPessoas() {
        // Simulando um conjunto de pessoas
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Ana");
        pessoas.add(pessoa1);

        Pessoa pessoa2 = new Pessoa();

        pessoa2.setNome("Bob");

        pessoas.add(pessoa2);

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<Pessoa> resultado = pessoaService.listarTodasAsPessoas();

        assertEquals(pessoas.size(), resultado.size());
    }

    @Test
    void testListarImcMedioAgrupadoPorFaixaEtaria() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Alice");
        pessoa1.setPeso(60);
        pessoa1.setAltura(1.65);
        pessoa1.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Bob");
        pessoa2.setPeso(70);
        pessoa2.setAltura(1.75);
        pessoa2.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Carol");
        pessoa3.setPeso(55);
        pessoa3.setAltura(1.60);
        pessoa3.setDataNasc(LocalDate.of(2001, 1, 1));

        List<Pessoa> pessoas = Arrays.asList(
                pessoa1,
                pessoa2,
                pessoa3
        );

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        Agrupar agrupar = new Agrupar(pessoas);
        AgruparPorFaixaEtaria agruparPorFaixaEtaria = new AgruparPorFaixaEtaria(agrupar);
        Map<String, List<Pessoa>> pessoasPorFaixaEtaria = agruparPorFaixaEtaria.agrupar();

        Calc calc = new Calc(pessoasPorFaixaEtaria);


        Map<String, Double> resultado = pessoaService.listarImcMedioAgrupadoPorFaixaEtaria();

        assertEquals(calc.getImcMedioPorGrupoDePessoas(pessoasPorFaixaEtaria).size(), resultado.size());
    }

    @Test
    void testListarImcMedioAgrupadoPorSexoMaiorQue() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Alice");
        pessoa1.setSexo(Sexo.FEMININO);
        pessoa1.setPeso(61);
        pessoa1.setAltura(1.0);
        pessoa1.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Bob");
        pessoa2.setSexo(Sexo.MASCULINO);
        pessoa2.setPeso(70);
        pessoa2.setAltura(1.75);
        pessoa2.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Carol");
        pessoa3.setSexo(Sexo.FEMININO);
        pessoa3.setPeso(55);
        pessoa3.setAltura(1.60);
        pessoa3.setDataNasc(LocalDate.of(2001, 1, 1));

        List<Pessoa> pessoas = Arrays.asList(
                pessoa1,
                pessoa2,
                pessoa3
        );

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        Agrupar agrupar = new Agrupar(pessoas);
        AgruparPorFaixaEtaria agruparPorFaixaEtaria = new AgruparPorFaixaEtaria(agrupar);
        Map<String, List<Pessoa>> pessoasPorFaixaEtaria = agruparPorFaixaEtaria.agrupar();

        Calc calc = new Calc(pessoasPorFaixaEtaria);


        Map<String, Double> resultado = pessoaService.listarImcMaiorQuePorSexo(60.0);

        assertEquals(calc.getImcMedioPorGrupoDePessoas(pessoasPorFaixaEtaria).size(), resultado.size());
    }

    @Test
    void testListarIdadeMediaAgrupadoPorSexo() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Alice");
        pessoa1.setSexo(Sexo.FEMININO);
        pessoa1.setPeso(61);
        pessoa1.setAltura(1.0);
        pessoa1.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Bob");
        pessoa2.setSexo(Sexo.MASCULINO);
        pessoa2.setPeso(70);
        pessoa2.setAltura(1.75);
        pessoa2.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Carol");
        pessoa3.setSexo(Sexo.FEMININO);
        pessoa3.setPeso(55);
        pessoa3.setAltura(1.60);
        pessoa3.setDataNasc(LocalDate.of(2001, 1, 1));

        List<Pessoa> pessoas = Arrays.asList(
                pessoa1,
                pessoa2,
                pessoa3
        );

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        Agrupar agrupar = new Agrupar(pessoas);
        AgruparPorSexo agruparPorSexo = new AgruparPorSexo(agrupar);
        Map<String, List<Pessoa>> pessoasPorSexo = agruparPorSexo.agrupar();

        Calc calc = new Calc(pessoasPorSexo);


        Map<String, Double> resultado = pessoaService.listarIdadeMediaPorSexo();

        assertEquals(calc.getMediaDeIdadePorGrupo().size(), resultado.size());
    }

    @Test
    void testQuantidadeDeDoadoresPorReceptor() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Alice");
        pessoa1.setSexo(Sexo.FEMININO);
        pessoa1.setPeso(61);
        pessoa1.setAltura(1.0);
        pessoa1.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
        pessoa1.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Bob");
        pessoa2.setSexo(Sexo.MASCULINO);
        pessoa2.setPeso(70);
        pessoa2.setAltura(1.75);
        pessoa2.setTipoSanguineo(TipoSanguineo.B_POSITIVO);
        pessoa2.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Carol");
        pessoa3.setSexo(Sexo.FEMININO);
        pessoa3.setPeso(55);
        pessoa3.setAltura(1.60);
        pessoa3.setTipoSanguineo(TipoSanguineo.O_NEGATIVO);
        pessoa3.setDataNasc(LocalDate.of(2001, 1, 1));

        List<Pessoa> pessoas = Arrays.asList(
                pessoa1,
                pessoa2,
                pessoa3
        );

        when(pessoaRepository.findAll()).thenReturn(pessoas);


        Agrupar agrupar = new Agrupar(pessoas);
        AgruparPorTipoSanguineo agruparPorTipoSanguineo = new AgruparPorTipoSanguineo(agrupar);
        Map<String, List<Pessoa>> pessoasPorTipoSanguineo = agruparPorTipoSanguineo.agrupar();


        CompatibilidadeSanguinea compatibilidadeSanguinea = new CompatibilidadeSanguinea();
        Map<String, Integer> contagemDoadoresReceptorEsperada = compatibilidadeSanguinea.contagemDeDoadoresParaGrupoDeReceptor(pessoasPorTipoSanguineo);


        Map<String, Integer> resultado = pessoaService.quantidadeDeDoadoresPorReceptor(compatibilidadeSanguinea);


        assertEquals(contagemDoadoresReceptorEsperada.size(), resultado.size());

    }

    @Test
    void testDoadoresPorReceptor() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Alice");
        pessoa1.setSexo(Sexo.FEMININO);
        pessoa1.setPeso(61);
        pessoa1.setAltura(1.0);
        pessoa1.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
        pessoa1.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Bob");
        pessoa2.setSexo(Sexo.MASCULINO);
        pessoa2.setPeso(70);
        pessoa2.setAltura(1.75);
        pessoa2.setTipoSanguineo(TipoSanguineo.B_POSITIVO);
        pessoa2.setDataNasc(LocalDate.of(2001, 1, 1));

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Carol");
        pessoa3.setSexo(Sexo.FEMININO);
        pessoa3.setPeso(55);
        pessoa3.setAltura(1.60);
        pessoa3.setTipoSanguineo(TipoSanguineo.O_NEGATIVO);
        pessoa3.setDataNasc(LocalDate.of(2001, 1, 1));

        List<Pessoa> pessoas = Arrays.asList(
                pessoa1,
                pessoa2,
                pessoa3
        );

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        Agrupar agrupar = new Agrupar(pessoas);
        AgruparPorTipoSanguineo agruparPorTipoSanguineo = new AgruparPorTipoSanguineo(agrupar);
        Map<String, List<Pessoa>> pessoasPorTipoSanguineo = agruparPorTipoSanguineo.agrupar();

        Map<String, List<Pessoa>> resultado = pessoaService.doadoresPorReceptor(new CompatibilidadeSanguinea());

        assertEquals(pessoasPorTipoSanguineo.size(), resultado.size());
    }


    @Test
    void testEncontrarPessoaPorId() {

        Long id = 1L;

        Pessoa pessoaExemplo = new Pessoa();

        pessoaExemplo.setNome("Exemplo");
        pessoaExemplo.setSexo(Sexo.FEMININO);
        pessoaExemplo.setPeso(61);
        pessoaExemplo.setAltura(1.0);
        pessoaExemplo.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
        pessoaExemplo.setDataNasc(LocalDate.of(2001, 1, 1));


        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaExemplo));


        Optional<Pessoa> pessoaEncontrada = pessoaService.encontrarPessoaPorId(id);


        assertTrue(pessoaEncontrada.isPresent());


        assertEquals(pessoaExemplo, pessoaEncontrada.get());

        verify(pessoaRepository, times(1)).findById(id);
    }

    @Test
    @Transactional
    @Rollback
    void testSalvarPessoa() {


        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Alice");
        pessoa.setSexo(Sexo.FEMININO);
        pessoa.setPeso(61);
        pessoa.setAltura(1.0);
        pessoa.setTipoSanguineo(TipoSanguineo.A_POSITIVO);
        pessoa.setDataNasc(LocalDate.of(2001, 1, 1));

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);

        assertEquals(pessoa, pessoaSalva);

        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    @Transactional
    @Rollback
    void testDeletarPessoa() {
        Long idExemplo = 1L;

        pessoaService.deletarPessoa(idExemplo);

        verify(pessoaRepository, times(1)).deleteById(idExemplo);
    }



}

