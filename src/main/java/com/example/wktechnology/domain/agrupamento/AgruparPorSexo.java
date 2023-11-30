package com.example.wktechnology.domain.agrupamento;

import com.example.wktechnology.model.entity.Pessoa;
import com.example.wktechnology.utils.enums.Sexo;

public class AgruparPorSexo {

    public static String getSexo(Pessoa pessoa){

        return pessoa.getSexo();

    }
}
