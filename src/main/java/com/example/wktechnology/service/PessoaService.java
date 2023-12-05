package com.example.wktechnology.service;

import com.example.wktechnology.domain.agrupamento.AgruparPorFaixaEtaria;
import com.example.wktechnology.domain.agrupamento.AgruparPorSexo;
import com.example.wktechnology.domain.agrupamento.AgruparPorTipoSanguineo;
import com.example.wktechnology.domain.indicadores.Media;
import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.model.repository.PessoaRepository;
import com.example.wktechnology.domain.agrupamento.Agrupar;
import com.example.wktechnology.domain.calculo.Calc;
import com.example.wktechnology.domain.indicadores.Imc;
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
        return pessoaRepository.findAll();
    }

    public Map<String, Double> listarImcMedioAgrupadoPorFaixaEtaria(){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Agrupar agrupar = new Agrupar(pessoas);

        AgruparPorFaixaEtaria agruparPorFaixaEtaria = new AgruparPorFaixaEtaria(agrupar);

        Map<String, List<Pessoa>> pessoasPorFaixaEtaria = agruparPorFaixaEtaria.agrupar();

        Calc cal = new Calc(pessoasPorFaixaEtaria);

        return cal.getImcMedioPorGrupoDePessoas(pessoasPorFaixaEtaria);
    }

    public Map<String, Double> listarImcMaiorQuePorSexo(Double limite){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Agrupar agrupar = new Agrupar(pessoas);

        AgruparPorSexo agruparPorSexo = new AgruparPorSexo(agrupar);

        Map<String, List<Pessoa>> pessoasPorSexo = agruparPorSexo.agrupar();

        Calc calc = new Calc(pessoasPorSexo);

        return calc.getImcMedioPorGrupoDePessoasMaiorQue(pessoasPorSexo,limite);
    }

    public Map<String, Double> listarIdadeMediaPorSexo(){

        List<Pessoa> pessoas = listarTodasAsPessoas();

        Agrupar agrupar = new Agrupar(pessoas);

        AgruparPorSexo agruparPorSexo = new AgruparPorSexo(agrupar);

        Map<String, List<Pessoa>> pessoasPorSexo = agruparPorSexo.agrupar();

        Calc calc = new Calc(pessoasPorSexo);

        return calc.getMediaDeIdadePorGrupo();
    }

    public Map<String, Integer> quantidadeDeDoadoresPorReceptor(CompatibilidadeSanguinea compatibilidadeSanguinea){
        List<Pessoa> pessoas = listarTodasAsPessoas();

        Agrupar agrupar = new Agrupar(pessoas);

        AgruparPorTipoSanguineo agruparPorTipoSanguineo = new AgruparPorTipoSanguineo(agrupar);

        return compatibilidadeSanguinea.contagemDeDoadoresParaGrupoDeReceptor(agruparPorTipoSanguineo.agrupar());
    }

    public Map<String, List<Pessoa>> doadoresPorReceptor(CompatibilidadeSanguinea compatibilidadeSanguinea){
        List<Pessoa> pessoas = listarTodasAsPessoas();

        Agrupar agrupar = new Agrupar(pessoas);

        return agrupar.agruparPorTipoSanguineo();
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