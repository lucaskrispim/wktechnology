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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PessoaService {


    @Autowired
    private final PessoaRepository pessoaRepository;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Page<Pessoa> listarTodasAsPessoas(int offset, int pageSize) {
        return pessoaRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public List<Pessoa> listarTodasAsPessoas() {
        return (List<Pessoa>) pessoaRepository.findAll();
    }

    public Map<String, Double> listarImcMedioAgrupadoPorFaixaEtaria(){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<String, List<Pessoa>> pessoasPorFaixaEtaria = Agrupar.agruparPorFaixaEtaria(pessoas);

        return Imc.getImcMedio(pessoasPorFaixaEtaria);
    }
    public Map<String, Double> listarImcMaiorQuePorSexo(Double limite){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<String, List<Pessoa>> pessoasPorSexo = Agrupar.agruparPorSexo(pessoas);

        return Imc.getImcMaiorQueSeparadoPorSexo(pessoasPorSexo,limite);
    }

    public Map<String, Double> listarIdadeMediaPorSexo(){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<String, List<Pessoa>> pessoasPorTipoSanguineo = Agrupar.agruparPorSexo(pessoas);

        return Calc.getIdadeMediaPorTipoSanguineo(pessoasPorTipoSanguineo);
    }

    public Map<String, Integer> quantidadeDeDoadoresPorReceptor(CompatibilidadeSanguinea compatibilidadeSanguinea){
        List<Pessoa> pessoas = listarTodasAsPessoas();

        Map<String, List<Pessoa>> pessoasPorTipoSanguineo = Agrupar.agruparPorTipoSanguineo(pessoas);

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