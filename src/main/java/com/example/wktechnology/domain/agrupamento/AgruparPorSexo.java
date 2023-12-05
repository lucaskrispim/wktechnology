package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgruparPorSexo {

    private Agrupar agrupar;

    public  AgruparPorSexo(Agrupar agrupar){
        this.agrupar = agrupar;
    }

    public Map<String, List<Pessoa>> agrupar(){

        return agrupar.agruparPorSexo();
    }

    public static String getSexo(Pessoa pessoa){

        return pessoa.getSexo();

    }
}
