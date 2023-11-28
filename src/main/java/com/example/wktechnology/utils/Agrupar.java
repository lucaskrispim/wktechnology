package com.example.wktechnology.utils;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;
import com.example.wktechnology.utils.enums.TipoSanguineo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Agrupar{
    public static Map<String, List<Pessoa>> agruparPorFaixaEtaria(List<Pessoa> pessoas){

        return pessoas.stream()
                .collect(Collectors.groupingBy(p -> calcularGrupoPorIdade(p.getDataNasc())));
    }

    public static Map<String, List<Pessoa>> agruparPorSexo(List<Pessoa> pessoas){

        return pessoas.stream()
                .collect(Collectors.groupingBy(p -> calcularGrupoPorSexo(p.getSexo())));
    }

    public static Map<String, List<Pessoa>> agruparPorTipoSanguineo(List<Pessoa> pessoas){

        return pessoas.stream()
                .collect(Collectors.groupingBy(p -> calcularGrupoPorTipoSanguineo(p.getTipoSanguineo())));
    }

    private static String calcularGrupoPorSexo(String sexo){
        return Sexo.fromDescricao(sexo).getDescricao();
    }

    private static String calcularGrupoPorTipoSanguineo(String tipoSanguineo){
        return TipoSanguineo.fromDescricao(tipoSanguineo).getDescricao();
    }

    private static String calcularGrupoPorIdade(LocalDate dataNascimento) {
        int idade = getIdade(dataNascimento);
        int faixaEtaria = idade / 10 * 10;
        return faixaEtaria + "-" + (faixaEtaria + 9);
    }

    public static int getIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }
}
