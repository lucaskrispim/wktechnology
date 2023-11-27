package com.example.wktechnology.utils.enums;

public enum EstadoBrasileiro {
    AC("AC"),
    AL("AL"),
    AP("AP"),
    AM("AM"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PR("PR"),
    PE("PE"),
    PI("PI"),
    RJ("RJ"),
    RN("RN"),
    RS("RS"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    SE("SE"),
    TO("TO");

    private final String sigla;

    EstadoBrasileiro(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }


    public static EstadoBrasileiro fromSigla(String sigla) {
        for (EstadoBrasileiro estado : EstadoBrasileiro.values()) {
            if (estado.sigla.equalsIgnoreCase(sigla)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Sigla de estado inválida: " + sigla);
    }
}
