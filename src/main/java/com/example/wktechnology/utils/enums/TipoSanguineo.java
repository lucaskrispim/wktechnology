package com.example.wktechnology.utils.enums;

public enum TipoSanguineo {
        A_POSITIVO("A+"),
        A_NEGATIVO("A-"),
        B_POSITIVO("B+"),
        B_NEGATIVO("B-"),
        AB_POSITIVO("AB+"),
        AB_NEGATIVO("AB-"),
        O_POSITIVO("O+"),
        O_NEGATIVO("O-");

    private final String descricao;

    TipoSanguineo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para obter o Enum a partir da descrição
    public static TipoSanguineo fromDescricao(String descricao) {
        for (TipoSanguineo tipo : TipoSanguineo.values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("tipo de sangue inválido: " + descricao);
    }

    public static TipoSanguineo fromName(String descricao) {
        for (TipoSanguineo tipo : TipoSanguineo.values()) {
            if ( descricao.equalsIgnoreCase(tipo.name())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("tipo de sangue inválido: " + descricao);
    }

}
