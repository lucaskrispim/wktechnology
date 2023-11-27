package com.example.wktechnology.utils.enums;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não-Binário"),
    OUTRO("Outro");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para obter o Enum a partir da descrição
    public static Sexo fromDescricao(String descricao) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.descricao.equalsIgnoreCase(descricao)) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Descrição de sexo inválida: " + descricao);
    }
}
