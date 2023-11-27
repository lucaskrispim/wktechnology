package com.example.wktechnology.service;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.model.repository.PessoaRepository;
import com.example.wktechnology.utils.Agrupar;
import com.example.wktechnology.utils.Calc;
import com.example.wktechnology.utils.CompatibilidadeSanguinea;
import com.example.wktechnology.utils.Imc;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.wktechnology.utils.Agrupar.agruparPorFaixaEtaria;

@Service
public class PessoaService {


    @Autowired
    private final PessoaRepository pessoaRepository;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listarTodasAsPessoas() {
        return pessoaRepository.findAll();
    }

    public Map<String, Double> listarImcMedioAgrupadoPorFaixaEtaria(){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<String, List<Pessoa>> pessoasPorFaixaEtaria = Agrupar.agruparPorFaixaEtaria(pessoas);

        return Imc.getImcMedio(pessoasPorFaixaEtaria);
    }
    public Map<Sexo, Double> listarImcMaiorQuePorSexo(Double limite){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<Sexo, List<Pessoa>> pessoasPorSexo = Agrupar.agruparPorSexo(pessoas);

        return Imc.getImcMaiorQueSeparadoPorSexo(pessoasPorSexo,limite);
    }

    public Map<TipoSanguineo, Double> listarIdadeMediaPorSexo(){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<TipoSanguineo, List<Pessoa>> pessoasPorTipoSanguineo = Agrupar.agruparPorTipoSanguineo(pessoas);

        return Calc.getIdadeMediaPorTipoSanguineo(pessoasPorTipoSanguineo);
    }

    public Map<TipoSanguineo, Integer> quantidadeDeDoadoresPorReceptor(CompatibilidadeSanguinea compatibilidadeSanguinea){
        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<TipoSanguineo, List<Pessoa>> pessoasPorTipoSanguineo = Agrupar.agruparPorTipoSanguineo(pessoas);

        return compatibilidadeSanguinea.calcularDoadoresParaReceptor(pessoasPorTipoSanguineo);
    }

    public Optional<Pessoa> encontrarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    @Transactional
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

}