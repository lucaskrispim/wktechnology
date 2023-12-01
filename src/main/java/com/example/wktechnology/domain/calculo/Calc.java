package com.example.wktechnology.domain.calculo;

import com.example.wktechnology.domain.indicadores.Idade;
import com.example.wktechnology.domain.indicadores.Imc;
import com.example.wktechnology.domain.indicadores.Media;
import com.example.wktechnology.model.entity.Pessoa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calc {

    private final Map<String,List<Pessoa>> agrupamentoDePessoas;

    public Calc(Map<String, List<Pessoa>> agrupamentoDePessoas) {
        this.agrupamentoDePessoas = agrupamentoDePessoas;
    }

    public Map<String, Double> getMediaDeIdadePorGrupo(){
        Map<String, Double> idadeMediPorTipoSanguineo = new HashMap<>();
        agrupamentoDePessoas.forEach((grupo, listaPessoas) -> {

            List<Double> idadesList = listaPessoas.stream().mapToDouble(pessoa -> {
                Idade idade = new Idade(pessoa);
                return idade.calcular();
            }).boxed().toList();

            Media media = new Media( idadesList );

            idadeMediPorTipoSanguineo.put(grupo, media.calcular());
        });

        return idadeMediPorTipoSanguineo;
    }

    public Map<String, Double> getImcMedioPorGrupoDePessoas(Map<String, List<Pessoa>> pessoasAgrupadas){
        Map<String, Double> imcMedioPorgrupo = new HashMap<>();
        pessoasAgrupadas.forEach((faixaIdade, listaPessoas) -> {

            List<Double> imcList = listaPessoas.stream().mapToDouble(pessoa -> {
                Imc imc = new Imc(pessoa.getPeso(),pessoa.getAltura());
                return imc.calcular();
            }).boxed().toList();

            Media media = new Media( imcList );

            imcMedioPorgrupo.put(faixaIdade, media.calcular());

        });

        return imcMedioPorgrupo;
    }

    public Map<String, Double> getImcMedioPorGrupoDePessoasMaiorQue(Map<String, List<Pessoa>> pessoasAgrupadas,Double valor){
        Map<String, Double> imcMedioPorgrupo = new HashMap<>();
        pessoasAgrupadas.forEach((faixaIdade, listaPessoas) -> {

            List<Double> imcList = listaPessoas.stream().mapToDouble(pessoa -> {
                Imc imc = new Imc(pessoa.getPeso(),pessoa.getAltura());
                return imc.calcular();
            }).boxed().filter(imc -> imc > valor).toList();

            Media media = new Media( imcList );

            imcMedioPorgrupo.put(faixaIdade, media.calcular());

        });

        return imcMedioPorgrupo;
    }
}
