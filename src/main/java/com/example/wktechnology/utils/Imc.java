package com.example.wktechnology.utils;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Imc {

    public static Map<String, Double> getImcMedio(Map<String, List<Pessoa>> pessoasPorFaixaEtaria){
        Map<String, Double> imcMedioPorFaixaEtaria = new HashMap<>();
            pessoasPorFaixaEtaria.forEach((faixaIdade, listaPessoas) -> {
            double imcTotal = 0.0;
            for (Pessoa pessoa : listaPessoas) {
                double imc = calcularIMC(pessoa.getPeso(), pessoa.getAltura());
                imcTotal += imc;
            }
            double imcMedio = imcTotal / listaPessoas.size();
            imcMedioPorFaixaEtaria.put(faixaIdade, Math.round(imcMedio * 100.0) / 100.0);
        });

              return imcMedioPorFaixaEtaria;
    }

    public static Map<Sexo, Double> getImcMaiorQueSeparadoPorSexo(Map<Sexo, List<Pessoa>> pessoasPorSexo,Double limite){

        Map<Sexo, Double> imcMaiorQuePorSexo = new HashMap<>();

        pessoasPorSexo.forEach((sexo, listaPessoas) -> {
            double imcmaiorQue = 0.0;
            for (Pessoa pessoa : listaPessoas) {
                double imc = calcularIMC(pessoa.getPeso(), pessoa.getAltura());
                if (imc > limite){
                    imcmaiorQue  += 1.0;
                }

            }
            double imcmaiorQuePercentual = imcmaiorQue / listaPessoas.size();

            imcMaiorQuePorSexo.put(sexo, Math.round(imcmaiorQuePercentual * 100.0) / 100.0);
        });

        return imcMaiorQuePorSexo;
    }

    private static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }
}
